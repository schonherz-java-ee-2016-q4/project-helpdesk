package hu.schonherz.training.helpdesk.web.managedbeans.view;

import hu.schonherz.training.helpdesk.service.api.service.ClientActivityService;
import hu.schonherz.training.helpdesk.service.api.vo.ActivityTypeVO;
import hu.schonherz.training.helpdesk.service.api.vo.ClientActivityVO;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.util.Collection;

@ManagedBean(name = "clientActivityView")
@ViewScoped
@Data
@Slf4j
public class ClientActivityView {

    private ActivityTypeVO activityTypeVO;

    private boolean filtersCleared;

    private String dateFrom = "default";
    private String dateTo = "default";


    @PostConstruct
    public void create() {
        filtersCleared = true;
    }

    @EJB
    private ClientActivityService clientActivityService;

    public Collection<ClientActivityVO> getActivitiesByUserId(final String clientId) {
        return clientActivityService.findByClientIdOrderByCreatedAtDesc(clientId);
    }

    public Collection<ClientActivityVO> getActivitesByTarget() {
        if (filtersCleared || activityTypeVO == null) {
            return clientActivityService.findAll();
        }
        return clientActivityService.findByTypeOrderByCreatedAtDesc(activityTypeVO);
    }

    public Collection<ClientActivityVO> refreshSiteActivities(final String searchString) {
        return clientActivityService.findByTargetContainingOrderByCreatedAtDesc(searchString);
    }

    public void setActivityTypeVOWithString(final String typeAsString) {
        activityTypeVO = ActivityTypeVO.valueOf(typeAsString);
        filtersCleared = false;
    }

    public void printDates() {
//        Map<String, String> parameterMap = FacesContext.getCurrentInstance().
//            getExternalContext().getRequestParameterMap();
//        String dateFrom = parameterMap.get("filterDateFrom");
//        String dateTo = parameterMap.get("filterDateTo");
        log.info("dates recieved from the activity page:" + dateFrom + " " + dateTo);
    }

}
