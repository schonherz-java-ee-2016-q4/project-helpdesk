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
    private final LocalDateTime now = LocalDateTime.now();
    private int dayLoginSize = 0;
    private int weekLoginSize = 0;
    private int monthLoginSize = 0;
    private int dayConvsSize = 0;
    private int monthConvsSize = 0;
    private int weekConvsSize = 0;
    private static final int WEEK = 7;
    private static final int MONTH = 30;

    @PostConstruct
    public void createLoginDatas() {
        try {
            final String userName = getUser().getUsername();
            allLoginDates = rpcLoginStatisticsService.getAllLoginsOf(userName);
            final Long agentId = getUser().getProfileDetails().getId();
            allConversations = conversationService.findByAgentId(agentId.intValue());
        } catch (LoginDataRetrievalException e) {
            log.error("Couldn't retrieve the login dates for user {}!", getUser().getUsername(), e);
        }
    }

    public int getPastDayLogin() {
        for (int i = 0; i < allLoginDates.size(); i++) {
            if (allLoginDates.get(i).toLocalDate().equals(now.toLocalDate())) {
                dayLoginSize++;
            }
        }
        return dayLoginSize;
    }

    public int getPastWeekLogin() {
        for (int i = 0; i < allLoginDates.size(); i++) {
            if (allLoginDates.get(i).toLocalDate().isAfter(now.minusDays(WEEK).toLocalDate())) {
                weekLoginSize++;
            }
        }
        return weekLoginSize;
    }

    public int getPastMonthLogin() {
        for (int i = 0; i < allLoginDates.size(); i++) {
            if (allLoginDates.get(i).toLocalDate().isAfter(now.minusDays(MONTH).toLocalDate())) {
                monthLoginSize++;
            }
        }
        return monthLoginSize;
    }

    public int getPastDayConvs() {
        for (int i = 0; i < allConversations.size(); i++) {
            if (allConversations.get(i).getBegindate().toLocalDate().equals(now.toLocalDate())) {
                dayConvsSize++;
            }

        }
        return dayConvsSize;
    }

    public int getPastWeekConvs() {
        for (int i = 0; i < allConversations.size(); i++) {
            if (allConversations.get(i).getBegindate().toLocalDate().isAfter(now.minusDays(WEEK).toLocalDate())) {
                weekConvsSize++;
            }
        }
        return weekConvsSize;
    }
    public int getPastMonthConvs() {
        for (int i = 0; i < allConversations.size(); i++) {
            if (allConversations.get(i).getBegindate().toLocalDate().isAfter(now.minusDays(MONTH).toLocalDate())) {
                monthConvsSize++;
            }
        }
        return monthConvsSize;
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
