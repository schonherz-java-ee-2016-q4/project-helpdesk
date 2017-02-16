package hu.schonherz.training.helpdesk.web.managedbeans.view;

import hu.schonherz.training.helpdesk.service.api.service.ClientActivityService;
import hu.schonherz.training.helpdesk.service.api.service.ConversationService;
import hu.schonherz.training.helpdesk.service.api.vo.ClientActivityVO;
import hu.schonherz.training.helpdesk.service.api.vo.ConversationTypeVO;
import hu.schonherz.training.helpdesk.service.api.vo.ConversationVO;
import hu.schonherz.training.helpdesk.web.security.domain.AgentUser;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.util.Collection;
import java.util.List;

@ManagedBean(name = "clientActivityView")
@ViewScoped
public class ClientActivityView {

    @EJB
    private ConversationService conversationService;

    private AgentUser user;

    @PostConstruct
    public void init() {
        user = (AgentUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }


    @EJB
    private ClientActivityService clientActivityService;

    public Collection<ClientActivityVO> refreshActivities(final String clientId) {
        return clientActivityService.findByClientIdOrderByCreatedAtDesc(clientId);
    }

    public Collection<ClientActivityVO> refreshSiteActivities(final String searchString) {
        return clientActivityService.findByTargetContainingOrderByCreatedAtDesc(searchString);
    }

    public ConversationVO getOpenConversation() {
        List<ConversationVO> conversationList = (List<ConversationVO>) conversationService.findByAgentId(user.getProfileDetails().getId());
        ConversationVO openConversation = null;
        for (ConversationVO tmpConversation : conversationList) {
            if (!tmpConversation.getType().equals(ConversationTypeVO.CLOSED)) {
                openConversation = tmpConversation;
                break;
            }
        }
        return openConversation;
    }


}
