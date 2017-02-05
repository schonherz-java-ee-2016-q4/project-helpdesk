package hu.schonherz.training.helpdesk.web.view;

import hu.schonherz.training.helpdesk.service.api.service.ConversationService;
import hu.schonherz.training.helpdesk.service.api.service.MessageService;
import hu.schonherz.training.helpdesk.service.api.vo.MessageVO;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@ManagedBean(name = "chatView")
@ViewScoped
@Data
@NoArgsConstructor
public class ChatView {

    @EJB
    private MessageService messageService;

    private int agentId = 55;
    private String clientId = "46m9z";
    private String content;
    private LocalDateTime now = LocalDateTime.now();

    public void send(){
        MessageVO message = new MessageVO();
        message.setContent(content);
        message.setAgentId(agentId);
        message.setClientId(clientId);
        message.setSendDate(now);
        messageService.save(message);
    }
    public Collection<MessageVO> getMessages(){
            List<MessageVO> list = (List<MessageVO>)messageService.findMessages(agentId,clientId);
            return list;
    }

}
