package hu.schonherz.training.helpdesk.web.managedbeans.view;

import hu.schonherz.training.helpdesk.service.api.service.ClientActivityService;
import hu.schonherz.training.helpdesk.service.api.vo.ActivityTypeVO;
import hu.schonherz.training.helpdesk.service.api.vo.ClientActivityVO;
import lombok.Data;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.util.Collection;

@ManagedBean(name = "clientActivityView")
@ViewScoped
@Data
public class ClientActivityView {

    private ActivityTypeVO activityTypeVO;

    @EJB
    private ClientActivityService clientActivityService;

    public Collection<ClientActivityVO> getActivitiesByUserId(final String clientId) {
        return clientActivityService.findByClientIdOrderByCreatedAtDesc(clientId);
    }

    public Collection<ClientActivityVO> getActivitesByTarget() {
        if (activityTypeVO == null) {
            return clientActivityService.findAll();
        }
        return clientActivityService.findByTypeOrderByCreatedAtDesc(activityTypeVO);
    }

    public Collection<ClientActivityVO> refreshSiteActivities(final String searchString) {
        return clientActivityService.findByTargetContainingOrderByCreatedAtDesc(searchString);
    }

    public void setActivityTypeVOWithString(final String typeAsString) {
        activityTypeVO = ActivityTypeVO.valueOf(typeAsString);
    }

}
