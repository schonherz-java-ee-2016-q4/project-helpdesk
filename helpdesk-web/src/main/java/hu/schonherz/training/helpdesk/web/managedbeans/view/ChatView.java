package hu.schonherz.training.helpdesk.web.managedbeans.view;

import hu.schonherz.training.helpdesk.service.api.service.ConversationService;
import hu.schonherz.training.helpdesk.service.api.service.MessageService;
import hu.schonherz.training.helpdesk.service.api.vo.ConversationTypeVO;
import hu.schonherz.training.helpdesk.service.api.vo.ConversationVO;
import hu.schonherz.training.helpdesk.service.api.vo.MessageVO;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.security.Principal;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Slf4j
@Data
@NoArgsConstructor
@ManagedBean(name = "chatView")
@ViewScoped
public class ChatView {
    private static final String SENT_BY_AGENT = "agent";
    private static final String SENT_BY_CLIENT = "client";

    @EJB
    private MessageService messageService;

    @EJB
    private ConversationService conversationService;

    private String content;
    private Boolean isAgent;
    private List<MessageVO> messageList;
    private Long conversationId;
    private ConversationVO conversationVO;

    @PostConstruct
    public void init() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        Principal principal = request.getUserPrincipal();
        isAgent = principal != null;

        if (request.getParameterMap().containsKey("id")) {
            conversationId = Long.parseLong(request.getParameterMap().get("id")[0]);
        }

        conversationVO = conversationService.findById(conversationId);
        if (conversationVO != null) {
            messageList = (List<MessageVO>) messageService.findMessages(conversationVO.getAgentId(), conversationVO.getClientId());
        }
    }

    public void send() {
        if (content.isEmpty()) {
            return;
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

        if (conversationVO.getType().equals(ConversationTypeVO.CLOSED) && !isAgent) {
            clientRedirect();
            return null;
        }

        messageList = (List<MessageVO>) messageService.findMessages(
                conversationVO.getAgentId(),
                conversationVO.getClientId());

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

    public boolean isAvailableMessage() {
        return !(messageList == null || messageList.isEmpty());
    }

    public void updateConversation() {
        conversationVO.setType(ConversationTypeVO.CLOSED);
        conversationService.save(conversationVO);
    }

    public boolean isThereId() {
        return !(conversationVO == null || conversationVO.getType().equals(ConversationTypeVO.CLOSED));
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
