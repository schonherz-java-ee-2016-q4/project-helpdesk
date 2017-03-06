package hu.schonherz.training.helpdesk.web.managedbeans.view;

import hu.schonherz.javatraining.issuetracker.shared.api.ForHelpdeskServiceRemote;
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
@SuppressWarnings("PMD")
public class StatisticsView {
    private static final int DAYS_IN_WEEK = 7;
    private static final int DAYS_IN_MONTH = 30;

    @EJB(lookup = BeanConstants.JNDI_LOGIN_STATISTICS_SERVICE)
    private RpcLoginStatisticsService rpcLoginStatisticsService;

    @EJB
    private ConversationService conversationService;

    @EJB(mappedName = "java:global/issue-tracker-ear-0.0.1-SNAPSHOT/issue-tracker-service-0.0.1-SNAPSHOT/ForHelpdeskServiceBean"
            + "!hu.schonherz.javatraining.issuetracker.shared.api.ForHelpdeskServiceRemote")
    private ForHelpdeskServiceRemote ticketServiceRemote;

    private List<LocalDateTime> agentLoginDates;
    private List<ConversationVO> agentConversations;
    private Calendar calendar;
    private Statistic statistic;
    private Date date;
    private AgentUser agent;

    @PostConstruct
    public void init() {
        agent = (AgentUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
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
            log.error("Couldn't retrieve the login dates for agent {}!", userName, e);
        }
        modifyStatistic();
    }

    public AgentUser getUser() {
        return this.agent;
    }

    public void calcDailyLogin() {
        int dayLoginSize = 0;
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

    public void calcDailyTickets() {
        Date start, end;
        Calendar c = (Calendar) calendar.clone();
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        start = c.getTime();
        c.add(Calendar.DAY_OF_MONTH, 1);
        end = c.getTime();
        log.info("Day: " + calendar.getTime() + " Current Day: " + start + " - " + end);
        statistic.setNumberOfDailyConversations(ticketServiceRemote.getNumberOfCreatedTicketsByUser(getUser().getUsername(), start, end));
    }

    public void calcWeeklyTickets() {
        Calendar c = (Calendar) calendar.clone();
        c.set(Calendar.HOUR_OF_DAY, 0); // ! clear would not reset the hour of day !
        c.clear(Calendar.MINUTE);
        c.clear(Calendar.SECOND);
        c.clear(Calendar.MILLISECOND);
        c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek());
        Date start = c.getTime();
        c.add(Calendar.DAY_OF_MONTH, DAYS_IN_WEEK);
        Date end = c.getTime();
        log.info("Day: " +  calendar.getTime() + " Currenth Week:" + start + " - " + end);
        statistic.setNumberOfWeeklyConversations(ticketServiceRemote.getNumberOfCreatedTicketsByUser(getUser().getUsername(), start, end));
    }

    public void calcMonthlyTickets() {
        Calendar c = (Calendar) calendar.clone();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = 1;
        c.set(year, month, day);
        int numOfDaysInMonth = c.getActualMaximum(Calendar.DAY_OF_MONTH);
        Date start = c.getTime();
        c.add(Calendar.DAY_OF_MONTH, numOfDaysInMonth - 1);
        Date end = c.getTime();
        log.info("Day: " +  calendar.getTime() + " Currenth Month: " + start + " - " + end);
        statistic.setNumberOfMonthlyTickets(ticketServiceRemote.getNumberOfCreatedTicketsByUser(getUser().getUsername(), start, end));
    }

    public void modifyStatistic() {
        calendar.setTime(date);
        calcDailyLogin();
        calcWeeklyLogin();
        calcMonthlyLogin();
        calcDailyConversations();
        calcWeeklyConversations();
        calcMonthlyConversations();
        calcDailyTickets();
        calcWeeklyTickets();
        calcMonthlyTickets();
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
