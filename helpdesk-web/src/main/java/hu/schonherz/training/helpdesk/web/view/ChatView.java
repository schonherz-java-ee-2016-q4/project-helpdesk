package hu.schonherz.training.helpdesk.web.view;

import hu.schonherz.training.helpdesk.service.api.service.ConversationService;
import hu.schonherz.training.helpdesk.service.api.service.MessageService;
import hu.schonherz.training.helpdesk.service.api.vo.ConversationVO;
import hu.schonherz.training.helpdesk.service.api.vo.MessageVO;
import lombok.Data;
import lombok.NoArgsConstructor;

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
import java.util.Map;

@ManagedBean(name = "chatView")
@ViewScoped
@Data
@NoArgsConstructor
public class ChatView {
    @EJB
    private MessageService messageService;
    @EJB
    private ConversationService conversationService;

    private ConversationVO conversationVO;
    private String content;
    private Boolean isAgent;
    private Long conversationId;
    private List<MessageVO> messageList;
    boolean hasId = true;


    @PostConstruct
    public void init() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        Principal principal = request.getUserPrincipal();
        isAgent = principal != null;
        if(request.getParameterMap().containsKey("id")) {
            conversationId = Long.parseLong(request.getParameterMap().get("id")[0]);
        }else{
            hasId = false;
        }
    }

    public String agentRedirect(){
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("agent/profile");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "profile.xhtml";
    }
    public boolean getMessageNum() {
        conversationVO = conversationService.findById(conversationId);
        messageList = (List<MessageVO>) messageService.findMessages(conversationVO.getAgentId(), conversationVO
                .getClientId());
        if (messageList == null || messageList.isEmpty()) {
            return false;
        }
        return true;
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
        conversationVO = conversationService.findById(conversationId);
        messageList = (List<MessageVO>) messageService.findMessages(conversationVO.getAgentId(), conversationVO
                .getClientId());
        MessageVO prev = messageList.get(0);

        if (prev.getSentBy().equals("client")) {
            prev.setNextMember("client");
        } else if (prev.getSentBy().equals("agent")) {
            prev.setNextMember("agent");
        }

        messageList.set(0, prev);

        for (int i = 1; i < messageList.size(); i++) {
            if (!messageList.get(i).getSentBy().equals(prev.getSentBy())) {
                messageList.get(i).setNextMember(messageList.get(i).getSentBy());
            } else {
                messageList.get(i).setNextMember(null);
            }
            prev = messageList.get(i);
        }
        return messageList;
    }

    public void updateConversation() {
        conversationVO.setClosed(true);
        conversationService.save(conversationVO);
    }

    public boolean isThereId(){
        if(conversationId >= 0 || hasId ){
            return true;
        }
        return false;
    }
}
