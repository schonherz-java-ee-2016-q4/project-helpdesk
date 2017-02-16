package hu.schonherz.training.helpdesk.web.managedbeans.view;

import hu.schonherz.training.helpdesk.service.api.service.ClientActivityService;
import hu.schonherz.training.helpdesk.service.api.vo.ClientActivityVO;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.util.Collection;

@ManagedBean(name = "clientActivityView")
@ViewScoped
public class ClientActivityView {

    @EJB
    private ClientActivityService clientActivityService;

    public Collection<ClientActivityVO> refreshActivities(final String clientId) {
        return clientActivityService.findByClientIdOrderByCreatedAtDesc(clientId);
    }

    public Collection<ClientActivityVO> refreshSiteActivities(final String searchString) {
        return clientActivityService.findByTargetContainingOrderByCreatedAtDesc(searchString);
    }


}
