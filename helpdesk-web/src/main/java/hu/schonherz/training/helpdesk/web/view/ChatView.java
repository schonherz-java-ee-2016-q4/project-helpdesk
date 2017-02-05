package hu.schonherz.training.helpdesk.web.view;

import hu.schonherz.training.helpdesk.service.api.service.ConversationService;
import hu.schonherz.training.helpdesk.service.api.service.MessageService;
import hu.schonherz.training.helpdesk.service.api.vo.ConversationVO;
import hu.schonherz.training.helpdesk.service.api.vo.MessageVO;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.time.LocalDateTime;
import java.util.Collection;

@ManagedBean(name = "chatView")
@ViewScoped
@Data
@NoArgsConstructor
public class ChatView {
    private final Logger LOGGER = LoggerFactory.getLogger(ChatView.class);
    @EJB
    private MessageService messageService;
    @EJB
    private ConversationService conversationService;

    private ConversationVO conversationVO;
    private String content;

    public void send() {
        MessageVO message = new MessageVO();
        message.setContent(content);
        message.setAgentId(conversationVO.getAgentId());
        message.setClientId(conversationVO.getClientId());
        message.setSendDate(LocalDateTime.now());
        message.setConversation(conversationVO);
        messageService.save(message);
    }

    public Collection<MessageVO> getMessages() {
        conversationVO = conversationService.findById(Long.parseLong("5"));
        return conversationVO.getMessages();
    }

}
