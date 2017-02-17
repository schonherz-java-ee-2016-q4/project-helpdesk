package hu.schonherz.training.helpdesk.web.managedbeans.view;

import hu.schonherz.project.admin.service.api.rpc.LoginDataRetrievalException;
import hu.schonherz.project.admin.service.api.rpc.RpcLoginStatisticsService;
import hu.schonherz.training.helpdesk.web.security.domain.AgentUser;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@ManagedBean(name = "statisticsView")
@ViewScoped
@Data
@NoArgsConstructor
public class StatisticsView {
    @EJB(lookup = "java:global/admin-ear-0.0.1-SNAPSHOT/admin-service-0.0.1-SNAPSHOT/RpcLoginStatisticsBean")
    private RpcLoginStatisticsService rpcLoginStatisticsService;

    private List<LocalDateTime> allLoginDates;
    private List<LocalDate> allDate;
    LocalDate today = LocalDate.now();
    private int dayLoginSize = 0;
    private int weekLoginSize = 0;
    private int monthLoginSize = 0;

    @PostConstruct
    public void createLoginDatas() {
        try {
            final String userName = getUser().getUsername();
            allLoginDates = rpcLoginStatisticsService.getAllLoginsOf(userName);


        } catch (LoginDataRetrievalException e) {
            log.error("Couldn't retrieve the login dates for user {}!", getUser().getUsername(), e);
        }
    }

    public int getPastDayStats() {
        LocalDateTime now = LocalDateTime.now();
        for (int i = 0; i < allLoginDates.size(); i++) {
            if (allLoginDates.get(i).toLocalDate().equals(now.toLocalDate())) {
                dayLoginSize++;
            }
        }
        return dayLoginSize;
    }

    public int getPastWeekStats(){
        LocalDateTime now = LocalDateTime.now().minusDays(7);
        for (int i = 0; i < allLoginDates.size(); i++) {
            if (allLoginDates.get(i).toLocalDate().isAfter(now.toLocalDate())) {
                weekLoginSize++;
            }
        }
        return weekLoginSize;
    }
    public int getPastMonthStats(){
        LocalDateTime now = LocalDateTime.now().minusDays(30);
        for (int i = 0; i < allLoginDates.size(); i++) {
            if (allLoginDates.get(i).toLocalDate().isAfter(now.toLocalDate())) {
                monthLoginSize++;
            }
        }
        return monthLoginSize;
    }

    public List<LocalDateTime> getThisMonthLogins() {
        LocalDateTime actualDateTime = LocalDateTime.now();
        LocalDateTime firstDayOfThisMonth = actualDateTime.with(TemporalAdjusters.firstDayOfMonth())
                .withHour(0).withMinute(0).withSecond(0);
        return allLoginDates.stream()
                .filter(e -> e.isAfter(firstDayOfThisMonth))
                .collect(Collectors.toList());
    }

    public AgentUser getUser() {
        return (AgentUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
