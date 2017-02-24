package hu.schonherz.training.helpdesk.web.managedbeans.view;

import hu.schonherz.project.admin.service.api.rpc.LoginDataRetrievalException;
import hu.schonherz.project.admin.service.api.rpc.RpcLoginStatisticsService;
import hu.schonherz.training.helpdesk.service.api.service.ConversationService;
import hu.schonherz.training.helpdesk.service.api.vo.ConversationVO;
import hu.schonherz.training.helpdesk.web.BeanConstants;
import hu.schonherz.training.helpdesk.web.domain.Statistic;
import hu.schonherz.training.helpdesk.web.security.domain.AgentUser;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.time.LocalDateTime;
import java.time.temporal.WeekFields;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@Slf4j
@Data
@NoArgsConstructor
@ViewScoped
@ManagedBean(name = "statisticsView")
public class StatisticsView {
    private static final int DAYS_IN_WEEK = 7;
    private static final int DAYS_IN_MONTH = 30;

    @EJB(lookup = BeanConstants.JNDI_LOGIN_STATISTICS_SERVICE)
    private RpcLoginStatisticsService rpcLoginStatisticsService;

    @EJB
    private ConversationService conversationService;

    private List<LocalDateTime> agentLoginDates;
    private List<ConversationVO> agentConversations;
    private Calendar calendar;
    private Statistic statistic;
    private Date date;

    @PostConstruct
    public void init() {
        statistic = new Statistic();
        date = new Date();
        calendar = Calendar.getInstance();
        calendar.setTime(date);
        final String userName = getUser().getUsername();
        final Long agentId = getUser().getProfileDetails().getId();

        try {
            agentLoginDates = rpcLoginStatisticsService.getAllLoginsOf(userName);
            agentConversations = conversationService.findByAgentId(agentId);
        } catch (LoginDataRetrievalException e) {
            log.error("Couldn't retrieve the login dates for user {}!", userName, e);
        }
        modifyStatistic();
    }

    public AgentUser getUser() {
        return (AgentUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    public void calcDailyLogin() {
        int dayLoginSize = 0;
        log.info("évv: " + calendar.get(Calendar.WEEK_OF_YEAR) + "");
        for (LocalDateTime login : agentLoginDates) {

            if (login.getYear() == calendar.get(Calendar.YEAR)
                    && login.getMonth().getValue() == (calendar.get(Calendar.MONTH) + 1)
                    && login.getDayOfMonth() == calendar.get(Calendar.DAY_OF_MONTH)) {
                dayLoginSize++;
            }
        }
        statistic.setNumberOfDailyLogins(dayLoginSize);
    }

    public void calcWeeklyLogin() {
        int weekLoginSize = 0;
        for (LocalDateTime login : agentLoginDates) {
            WeekFields weekFields = WeekFields.of(Locale.getDefault());
            int weekNumber = login.toLocalDate().get(weekFields.weekOfWeekBasedYear());
            if (weekNumber == calendar.get(Calendar.WEEK_OF_YEAR)) {
                weekLoginSize++;
            }
        }
        statistic.setNumberOfWeeklyLogins(weekLoginSize);

    }

    public void calcMonthlyLogin() {
        int monthLoginSize = 0;
        for (LocalDateTime login : agentLoginDates) {
            if (login.getYear() == calendar.get(Calendar.YEAR)
                    && login.getMonth().getValue() == (calendar.get(Calendar.MONTH) + 1)) {
                monthLoginSize++;
            }
        }
        statistic.setNumberOfMonthlyLogins(monthLoginSize);
    }

    public void calcDailyConversations() {
        int dayConvsSize = 0;
        for (ConversationVO conversation : agentConversations) {
            if (conversation.getCreatedAt().getYear() == calendar.get(Calendar.YEAR)
                    && conversation.getCreatedAt().getMonth().getValue() == (calendar.get(Calendar.MONTH) + 1)
                    && conversation.getCreatedAt().getDayOfMonth() == calendar.get(Calendar.DAY_OF_MONTH)) {
                dayConvsSize++;
            }
        }
        statistic.setNumberOfDailyConversations(dayConvsSize);
    }

    public void calcWeeklyConversations() {
        int weekConvsSize = 0;
        for (ConversationVO conversation : agentConversations) {
            WeekFields weekFields = WeekFields.of(Locale.getDefault());
            int weekNumber = conversation.getCreatedAt().toLocalDate().get(weekFields.weekOfWeekBasedYear());
            if (weekNumber == calendar.get(Calendar.WEEK_OF_YEAR)) {
                weekConvsSize++;
            }
        }
        statistic.setNumberOfWeeklyConversations(weekConvsSize);
    }

    public void calcMonthlyConversations() {
        int monthConvsSize = 0;
        for (ConversationVO conversation : agentConversations) {
            if (conversation.getCreatedAt().getYear() == calendar.get(Calendar.YEAR)
                    && conversation.getCreatedAt().getMonth().getValue() == (calendar.get(Calendar.MONTH) + 1)) {
                monthConvsSize++;
            }
        }
        statistic.setNumberOfMonthlyConversations(monthConvsSize);
    }

    public void modifyStatistic() {
        calendar.setTime(date);
        calcDailyLogin();
        calcWeeklyLogin();
        calcMonthlyLogin();
        calcDailyConversations();
        calcWeeklyConversations();
        calcMonthlyConversations();
    }

    public void changeDate(final String type, final String operation) {
        log.info("Date change " + type + " " + operation);
        if ("day".equals(type)) {
            if ("sub".equals(operation)) {
                calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) - 1);
            } else if ("add".equals(operation)) {
                calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) + 1);
            }
        }
        if ("month".equals(type)) {
            if ("sub".equals(operation)) {
                calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) - 1);
            } else if ("add".equals(operation)) {
                calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) + 1);
            }
        }
        if ("week".equals(type)) {
            if ("sub".equals(operation)) {
                calendar.set(Calendar.WEEK_OF_YEAR, calendar.get(Calendar.WEEK_OF_YEAR) - 1);
            } else if ("add".equals(operation)) {
                calendar.set(Calendar.WEEK_OF_YEAR, calendar.get(Calendar.WEEK_OF_YEAR) + 1);
            }
        }
        date = calendar.getTime();
        modifyStatistic();
    }
}
