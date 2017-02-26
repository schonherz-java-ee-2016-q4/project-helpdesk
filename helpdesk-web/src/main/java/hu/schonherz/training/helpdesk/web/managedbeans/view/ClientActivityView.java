package hu.schonherz.training.helpdesk.web.managedbeans.view;

import hu.schonherz.training.helpdesk.service.api.service.ClientActivityService;
import hu.schonherz.training.helpdesk.service.api.vo.ActivityTypeVO;
import hu.schonherz.training.helpdesk.service.api.vo.ClientActivityVO;
import hu.schonherz.training.helpdesk.web.managedbeans.domain.ClientActivityFilterType;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.EnumSet;

@ManagedBean(name = "clientActivityView")
@ViewScoped
@Data
@Slf4j
public class ClientActivityView {

    private ActivityTypeVO activityTypeVO;

    private String dateFrom;
    private String dateTo;

    private LocalDateTime localFrom;
    private LocalDateTime localTo;

    @EJB
    private ClientActivityService clientActivityService;

    private EnumSet<ClientActivityFilterType> filters;

    private static final String FORMAT_STRING = "yyyy-MM-dd HH:mm";
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern(FORMAT_STRING);

    @PostConstruct
    public void init() {
        filters = EnumSet.noneOf(ClientActivityFilterType.class);
    }

    public Collection<ClientActivityVO> getActivitiesByUserId(final String clientId) {
        return clientActivityService.findByClientIdOrderByCreatedAtDesc(clientId);
    }

    public Collection<ClientActivityVO> applyFilters() {
        if (filters.isEmpty()) {
            return clientActivityService.findAllByOrderByCreatedAtDesc();
        } else if (filters.contains(ClientActivityFilterType.FILTER_BY_TYPE) && filters.contains(ClientActivityFilterType.FILTER_BY_DATE_RANGE)) {
            log.info("Filtering by date range and type");
            return clientActivityService.findByDateRangeAndActivityType(localFrom, localTo, activityTypeVO);
        } else if (filters.contains(ClientActivityFilterType.FILTER_BY_DATE_RANGE)) {
            log.info("Filtering by date range");
            return clientActivityService.findByCreatedAtBetweenOrderByCreatedAtDesc(localFrom, localTo);
        }
        log.info("Filtering by type");
        return clientActivityService.findByTypeOrderByCreatedAtDesc(activityTypeVO);
    }

    public Collection<ClientActivityVO> refreshSiteActivities(final String searchString) {
        return clientActivityService.findByTargetContainingOrderByCreatedAtDesc(searchString);
    }

    public void setActivityTypeVOWithString(final String typeAsString) {
        activityTypeVO = ActivityTypeVO.valueOf(typeAsString);
        filters.add(ClientActivityFilterType.FILTER_BY_TYPE);
    }

    public void printDates() {
        log.info("dates recieved from the activity page: {}, {}", dateFrom, dateTo);

        localFrom = LocalDateTime.parse(dateFrom, FORMATTER);
        localTo = LocalDateTime.parse(dateTo, FORMATTER);
        filters.add(ClientActivityFilterType.FILTER_BY_DATE_RANGE);
        log.info("LocalDateTime objects converted: {}, {}", localFrom, localTo);

    }

}
