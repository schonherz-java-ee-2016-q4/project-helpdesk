package hu.schonherz.training.helpdesk.web.managedbeans.view;

import java.io.IOException;
import java.security.Principal;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.context.SecurityContextHolder;

import hu.schonherz.javatraining.issuetracker.client.api.service.ticket.TicketServiceRemote;
import hu.schonherz.javatraining.issuetracker.client.api.vo.TicketVo;
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

@Slf4j
@Data
@NoArgsConstructor
@ManagedBean(name = "chatView")
@ViewScoped
public class ChatView {
    private static final String SENT_BY_AGENT = "agent";
    private static final String SENT_BY_CLIENT = "client";

    @EJB(mappedName = "java:global/issue-tracker-ear-0.0.1-SNAPSHOT/issue-tracker-service-0.0.1-SNAPSHOT/TicketServiceBean!"
            + "hu.schonherz.javatraining.issuetracker.client.api.service.ticket.TicketServiceRemote")
    private TicketServiceRemote ticketServiceRemote;

    @EJB
    private MessageService messageService;

    @EJB
    private ConversationService conversationService;

    @ManagedProperty(value = "#{languageBean}")
    private LanguageBean localeManagerBean;

    private AgentUser user;
    private String content;
    private Boolean isAgent;
    private List<MessageVO> messageList;
    private Long conversationId;
    private ConversationVO conversationVO;
    private String issueName;
    private String issueDecription;
    private String issueType;

    @PostConstruct
    public void init() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        Principal principal = request.getUserPrincipal();
        log.error(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
        isAgent = principal != null;

        if (isAgent) {
            user = (AgentUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        }

        if (request.getParameterMap().containsKey("id")) {
            conversationId = Long.parseLong(request.getParameterMap().get("id")[0]);
        }

        conversationVO = conversationService.findById(conversationId);
        if (conversationVO != null) {
            messageList = (List<MessageVO>) messageService.findMessages(conversationVO.getAgentId(),
                    conversationVO.getClientId());
        }
    }

    public void createTicket() {
        AgentUser agent = (AgentUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        TicketVo ticketVo = new TicketVo();
        ticketVo.setTitle(issueName);
        ticketVo.setDescription(issueDecription);
        ticketVo.setUid(Long.toString(agent.getProfileDetails().getId()));
        ticketVo.setClientMail(conversationVO.getClientEmail());
        if (ticketServiceRemote.save(ticketVo, agent.getUsername()) == null) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", "Something went wrong..."));
        } else {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Successful", "The ticket has been saved!"));
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
        message.setConv(conversationVO);
        message.setSentBy(isAgent ? SENT_BY_AGENT : SENT_BY_CLIENT);

        messageService.save(message);
    }

    public Collection<MessageVO> getMessages() {
        ConversationVO conversationVO = conversationService.findById(conversationId);

        if (conversationVO.getStatus().equals(ConversationStatusVO.CLOSED) && !isAgent) {
            clientRedirect();
            return null;
        }

        messageList = (List<MessageVO>) messageService.findMessages(conversationVO.getAgentId(),
                conversationVO.getClientId());

        log.error(messageList.toString());

        if (messageList == null || messageList.isEmpty()) {
            MessageVO firstMessage = new MessageVO();
            firstMessage.setNextMember(SENT_BY_AGENT);
            firstMessage.setSentBy(SENT_BY_AGENT);
            firstMessage.setAgentId(conversationVO.getAgentId());
            firstMessage.setClientId(conversationVO.getClientId());
            firstMessage.setContent(localeManagerBean.localize("wait_for_agent"));
            firstMessage.setConv(conversationVO);
            firstMessage.setSendDate(LocalDateTime.now());
            messageService.save(firstMessage);
            messageList.add(firstMessage);
            log.error(firstMessage.toString());
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
}
