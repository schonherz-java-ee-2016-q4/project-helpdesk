package hu.schonherz.training.helpdesk.web.managedbeans.view;

import hu.schonherz.javatraining.issuetracker.shared.api.ForHelpdeskServiceRemote;
import hu.schonherz.javatraining.issuetracker.shared.vo.QuotaReachedException;
import hu.schonherz.javatraining.issuetracker.shared.vo.TicketData;
import hu.schonherz.project.admin.service.api.rpc.RpcAgentAvailabilityServiceRemote;
import hu.schonherz.project.admin.service.api.rpc.UsernameNotFoundException;
import hu.schonherz.training.helpdesk.service.api.service.ConversationService;
import hu.schonherz.training.helpdesk.service.api.service.MessageService;
import hu.schonherz.training.helpdesk.service.api.vo.ConversationStatusVO;
import hu.schonherz.training.helpdesk.service.api.vo.ConversationVO;
import hu.schonherz.training.helpdesk.service.api.vo.MessageVO;
import hu.schonherz.training.helpdesk.web.managedbeans.session.LanguageBean;
import hu.schonherz.training.helpdesk.web.security.domain.AgentUser;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.security.Principal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Slf4j
@Data
@NoArgsConstructor
@ViewScoped
@ManagedBean(name = "chatView")
public class ChatView {
    private static final String SENT_BY_AGENT = "agent";
    private static final String SENT_BY_CLIENT = "client";

    @EJB(mappedName = "java:global/issue-tracker-ear-0.0.1-SNAPSHOT/issue-tracker-service-0.0.1-SNAPSHOT/ForHelpdeskServiceBean"
            + "!hu.schonherz.javatraining.issuetracker.shared.api.ForHelpdeskServiceRemote")
    private ForHelpdeskServiceRemote ticketServiceRemote;

    @EJB(lookup = "java:global/admin-ear-0.0.1-SNAPSHOT/admin-service-0.0.1-SNAPSHOT/RpcAgentAvailabilityServiceBean!"
            + "hu.schonherz.project.admin.service.api.rpc.RpcAgentAvailabilityServiceRemote")
    private RpcAgentAvailabilityServiceRemote rpcAgentAvailabilityServiceRemote;

    @EJB
    private MessageService messageService;

    @EJB
    private ConversationService conversationService;

    @ManagedProperty(value = "#{languageBean}")
    private LanguageBean localeManagerBean;

    private AgentUser user;

    private Long conversationId;
    private ConversationVO conversationVO;
    private String content;
    private Boolean isAgent;
    private List<MessageVO> messageList;
    private String issueName;
    private String issueDescription;
    private String issueType;

    @SuppressWarnings("PMD")
    private List<String> issueTypes = new ArrayList<>();

    @PostConstruct
    public void init() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        Principal principal = request.getUserPrincipal();
        isAgent = principal != null;

        if (isAgent) {
            getUser();
        }

        if (request.getParameterMap().containsKey("id")) {
            conversationId = Long.parseLong(request.getParameterMap().get("id")[0]);
        }

        conversationVO = conversationService.findById(conversationId);
        if (conversationVO != null) {
            messageList = (List<MessageVO>) messageService.findMessages(conversationVO.getAgentId(),
                    conversationVO.getClientId());
        }

        issueTypes = ticketServiceRemote.getTypesByCompany(user.getProfileDetails().getCompany());
    }

    public void createTicket() {
        if (!isAgent) {
            return;
        }

        TicketData ticketData = new TicketData();
        ticketData.setCompanyName(user.getProfileDetails().getCompany());
        ticketData.setTicketName(issueName);
        ticketData.setTicketDescription(issueDescription);
        ticketData.setClientMail(conversationVO.getClientEmail());
        ticketData.setRecUser(user.getUsername());
        ticketData.setBindedUser(null);
        ticketData.setTicketTypeName("issueType");

        try {
            final boolean isCreationSuccessful = ticketServiceRemote.registerNewTicket(ticketData);

            if (!isCreationSuccessful) {
                errorMessage("Something went wrong!");
            } else {
                infoMessage("Successful save!");
            }
        } catch (QuotaReachedException e) {
            log.warn("Could not create ticket because company quota is reached!", e);
            errorMessage("Your company can't register more tickets!");
        }
    }

    public void send() {
        if (content.isEmpty()) {
            return;
        }

        if (isAgent && conversationVO.getStatus().equals(ConversationStatusVO.NEW)) {
            conversationVO.setStatus(ConversationStatusVO.IN_PROGRESS);
        }

        MessageVO message = new MessageVO();
        message.setContent(content);
        message.setAgentId(conversationVO.getAgentId());
        message.setClientId(conversationVO.getClientId());
        message.setSendDate(LocalDateTime.now());
        message.setConversation(conversationVO);
        message.setSentBy(isAgent ? SENT_BY_AGENT : SENT_BY_CLIENT);

        messageService.save(message);
    }

    public Collection<MessageVO> getMessages() {
        ConversationVO conversationVO = conversationService.findById(conversationId);

        if (conversationVO.getStatus().equals(ConversationStatusVO.CLOSED) && !isAgent) {
            clientRedirect();
            return null;
        }

        messageList = (List<MessageVO>) messageService.findMessages(
                conversationVO.getAgentId(),
                conversationVO.getClientId());

        log.error(messageList.toString());

        if (messageList == null || messageList.isEmpty()) {
            MessageVO firstMessage = new MessageVO();
            firstMessage.setNextMember(SENT_BY_AGENT);
            firstMessage.setSentBy(SENT_BY_AGENT);
            firstMessage.setAgentId(conversationVO.getAgentId());
            firstMessage.setClientId(conversationVO.getClientId());
            firstMessage.setContent(localeManagerBean.localize("wait_for_agent"));
            firstMessage.setConversation(conversationVO);
            firstMessage.setSendDate(LocalDateTime.now());
            messageService.save(firstMessage);
            messageList.add(firstMessage);
        }

        MessageVO prev = messageList.get(0);

        if (SENT_BY_CLIENT.equals(prev.getSentBy())) {
            prev.setNextMember(SENT_BY_CLIENT);
        } else if (SENT_BY_AGENT.equals(prev.getSentBy())) {
            prev.setNextMember(SENT_BY_AGENT);
        }

        messageList.set(0, prev);

        for (int i = 1; i < messageList.size(); i++) {
            MessageVO tmpMessage = messageList.get(i);

            if (!tmpMessage.getSentBy().equals(prev.getSentBy())) {
                tmpMessage.setNextMember(tmpMessage.getSentBy());
            } else {
                tmpMessage.setNextMember(null);
            }
            messageList.set(i, tmpMessage);
            prev = tmpMessage;
        }

        return messageList;
    }

    public void updateConversation() {
        try {
            rpcAgentAvailabilityServiceRemote.setAgentAvailabilityToTrue(user.getUsername());
        } catch (UsernameNotFoundException e) {
            log.error("Can't make the user available", e);
        }

        conversationVO.setStatus(ConversationStatusVO.CLOSED);
        conversationService.save(conversationVO);
    }

    public boolean isThereId() {
        return !(conversationVO == null || conversationVO.getStatus().equals(ConversationStatusVO.CLOSED));
    }

    public boolean isOwnConversation() {
        return isAgent ? conversationVO.getAgentId().equals(user.getProfileDetails().getId()) : true;
    }

    public void agentRedirect() {
        redirectTo("agent/profile");
    }

    public void clientRedirect() {
        redirectTo("https://www.google.hu");
    }

    private static void redirectTo(final String url) {
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect(url);
        } catch (IOException e) {
            log.error("Can't redirect to {}!", url, e);
        }
    }

    public AgentUser getUser() {
        return (AgentUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    private static void infoMessage(final String message) {
        FacesContext.getCurrentInstance()
                .addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "SUCCESS", message));
    }

    private static void errorMessage(final String message) {
        FacesContext.getCurrentInstance()
                .addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", message));
    }
}
