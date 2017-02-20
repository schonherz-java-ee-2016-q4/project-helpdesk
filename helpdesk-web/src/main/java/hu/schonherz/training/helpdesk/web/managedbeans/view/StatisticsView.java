package hu.schonherz.training.helpdesk.web.managedbeans.view;

import hu.schonherz.project.admin.service.api.rpc.LoginDataRetrievalException;
import hu.schonherz.project.admin.service.api.rpc.RpcLoginStatisticsService;
import hu.schonherz.training.helpdesk.service.api.service.ConversationService;
import hu.schonherz.training.helpdesk.service.api.vo.ConversationVO;
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

    @EJB
    private ConversationService conversationService;
    private List<LocalDateTime> allLoginDates;
    private List<ConversationVO> allConversations;
    private List<LocalDate> allDate;
    private int dayLoginSize;
    private int weekLoginSize;
    private int monthLoginSize;
    private int dayConvsSize;
    private int monthConvsSize;
    private int weekConvsSize;
    private static final int WEEK = 7;
    private static final int MONTH = 30;
    
    @PostConstruct
    public void createLoginDatas() {
        try {
            final String userName = getUser().getUsername();
            allLoginDates = rpcLoginStatisticsService.getAllLoginsOf(userName);
            final Long agentId = getUser().getProfileDetails().getId();
            allConversations = conversationService.findByAgentId(agentId.intValue());
            getPastMonth();
            getPastDay();
            getPastWeek();
        } catch (LoginDataRetrievalException e) {
            log.error("Couldn't retrieve the login dates for user {}!", getUser().getUsername(), e);
        }
    }


    private void getPastDay() {
        LocalDateTime now = LocalDateTime.now();
        dayLoginSize = 0;
        dayConvsSize = 0;
        for (LocalDateTime login : allLoginDates) {
            if (login.toLocalDate().equals(now.toLocalDate())) {
                dayLoginSize++;
            }
        }
        for (ConversationVO conversation : allConversations) {
            if (conversation.getBegindate().toLocalDate().equals(now.toLocalDate())) {
                dayConvsSize++;
            }
        }
    }

    private void getPastWeek() {
        LocalDateTime now = LocalDateTime.now().minusDays(WEEK);
        weekLoginSize = 0;
        weekConvsSize = 0;
        for (LocalDateTime login : allLoginDates) {
            if (login.toLocalDate().isAfter(now.toLocalDate())) {
                weekLoginSize++;
            }
        }
        for (ConversationVO conversation : allConversations) {
            if (conversation.getBegindate().toLocalDate().equals(now.toLocalDate())) {
                dayConvsSize++;
            }
        }

    }

    private void getPastMonth() {
        LocalDateTime now = LocalDateTime.now().minusDays(MONTH);
        monthLoginSize = 0;
        monthConvsSize = 0;
        for (LocalDateTime login : allLoginDates) {
            if (login.toLocalDate().isAfter(now.toLocalDate())) {
                monthLoginSize++;
            }
        }
        for (ConversationVO conversation : allConversations) {
            if (conversation.getBegindate().toLocalDate().isAfter(now.toLocalDate())) {
                monthConvsSize++;
            }
        }
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
