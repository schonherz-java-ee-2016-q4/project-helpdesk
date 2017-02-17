package hu.schonherz.training.helpdesk.web.managedbeans.view;

import hu.schonherz.training.helpdesk.service.api.service.ConversationService;
import hu.schonherz.training.helpdesk.service.api.vo.ConversationStatusVO;
import hu.schonherz.training.helpdesk.service.api.vo.ConversationVO;
import hu.schonherz.training.helpdesk.web.security.domain.AgentUser;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.util.List;

@ManagedBean(name = "chatPopupView")
@ViewScoped
public class ChatPopupView {

    @EJB
    private ConversationService conversationService;

    private AgentUser user;

    @PostConstruct
    public void init() {
        user = (AgentUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    public ConversationVO getOpenConversation() {
        List<ConversationVO> conversationList = (List<ConversationVO>) conversationService.findNotClosedConversations(user.getProfileDetails()
                .getId());
        ConversationVO openConversation = null;
        for (ConversationVO tmpConversation : conversationList) {
            if (!tmpConversation.getStatus().equals(ConversationStatusVO.CLOSED)) {
                openConversation = tmpConversation;
                break;
            }
        }
        return openConversation;
    }
}
