package hu.schonherz.training.helpdesk.web.view;

import hu.schonherz.training.helpdesk.service.api.service.ConversationService;
import hu.schonherz.training.helpdesk.service.api.service.MessageService;
import hu.schonherz.training.helpdesk.service.api.vo.ConversationVO;
import hu.schonherz.training.helpdesk.service.api.vo.MessageVO;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

@ManagedBean(name = "chatView")
@ViewScoped
@Data
@NoArgsConstructor
public class ChatView {
    @EJB
    private MessageService messageService;
    @EJB
    private ConversationService conversationService;

    private String content;
    private Boolean isAgent;
    private List<MessageVO> messageList;
    private Long conversationId;
    private ConversationVO conversationVO;
    private final Logger logger = LoggerFactory.getLogger(ChatView.class);

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

    public void agentRedirect() {
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("agent/profile");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void clientRedirect() {
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("https://www.google.hu");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean getMessageNum() {
        return !(messageList == null || messageList.isEmpty());
    }

    public void send() {
        MessageVO message = new MessageVO();
        message.setContent(content);
        message.setAgentId(conversationVO.getAgentId());
        message.setClientId(conversationVO.getClientId());
        message.setSendDate(LocalDateTime.now());
        message.setConv(conversationVO);
        message.setSentBy(isAgent ? "agent" : "client");
        messageService.save(message);
    }

    public Collection<MessageVO> getMessages() {
        ConversationVO conversationVO = conversationService.findById(conversationId);
        if (conversationVO.isClosed() && !isAgent) {
            clientRedirect();
            return null;
        }
        messageList = (List<MessageVO>) messageService.findMessages(conversationVO.getAgentId(), conversationVO
                .getClientId());
        MessageVO prev = messageList.get(0);

        if ("client".equals(prev.getSentBy())) {
            prev.setNextMember("client");
        } else if ("agent".equals(prev.getSentBy())) {
            prev.setNextMember("agent");
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
        conversationVO.setClosed(true);
        conversationService.save(conversationVO);
    }

    public boolean isThereId() {
        return !(conversationVO == null || conversationVO.isClosed());
    }

}
