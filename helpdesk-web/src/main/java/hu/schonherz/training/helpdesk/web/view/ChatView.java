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
import java.security.Principal;
import java.time.LocalDateTime;
import java.util.Collection;

@ManagedBean(name = "chatView")
@ViewScoped
@Data
@NoArgsConstructor
public class ChatView {
    Logger logger = LoggerFactory.getLogger(ChatView.class);

    @EJB
    private MessageService messageService;
    @EJB
    private ConversationService conversationService;

    private ConversationVO conversationVO;
    private String content;
    private Boolean isAgent;

    @PostConstruct
    public void init() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        Principal principal = request.getUserPrincipal();
        isAgent = principal != null;
//        logger.error("principal:" + isAgent);
//        fromUser = userServiceRemote.findByUsername(actUser);
//        List<UserVo> findAll = userServiceRemote.findAll();
//        findAll.remove(fromUser);
//        users = findAll;
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
        conversationVO = conversationService.findById(Long.parseLong("5"));
        return messageService.findMessages(conversationVO.getAgentId(), conversationVO.getClientId());
    }
}
