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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;

@ManagedBean(name = "clientActivityView")
@ViewScoped
@Data
@Slf4j
public class ClientActivityView {

    private ActivityTypeVO activityTypeVO;

    private boolean filtersCleared;

    private String dateFrom;
    private String dateTo;


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
        log.info("dates recieved from the activity page: {}, {}", dateFrom, dateTo);
        String formatString = "yyyy-MM-dd HH:mm";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(formatString);
        LocalDateTime localFrom = LocalDateTime.parse(dateFrom, formatter);
        LocalDateTime localTo = LocalDateTime.parse(dateTo, formatter);
        log.info("LocalDateTime objects converted: {}, {}", localFrom, localTo);
    }

}
