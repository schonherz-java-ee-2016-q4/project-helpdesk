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
import java.util.List;

@ManagedBean(name = "chatView")
@ViewScoped
@Data
@NoArgsConstructor
public class ChatView {
    Logger log = LoggerFactory.getLogger(ChatView.class);
    @EJB
    private MessageService messageService;
    @EJB
    private ConversationService conversationService;

    private ConversationVO conversationVO;
    private Long id = 5L;
    private String content;
    private LocalDateTime now = LocalDateTime.now();

    public void send(){
        MessageVO message = new MessageVO();
        message.setContent(content);
        message.setAgentId(conversationVO.getAgentId());
        message.setClientId(conversationVO.getClientId());
        message.setSendDate(now);
        message.setConversation(conversationVO);
        messageService.save(message);
    }
    public Collection<MessageVO> getMessages(){
            conversationVO = conversationService.findById(id);
            List<MessageVO> list = conversationVO.getMessages();
            log.error("A lista"+ list);
            return list;
    }

}
