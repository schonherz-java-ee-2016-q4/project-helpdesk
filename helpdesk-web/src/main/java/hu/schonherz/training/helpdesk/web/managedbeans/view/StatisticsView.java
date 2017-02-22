package hu.schonherz.training.helpdesk.web.managedbeans.view;

import hu.schonherz.project.admin.service.api.rpc.LoginDataRetrievalException;
import hu.schonherz.project.admin.service.api.rpc.RpcLoginStatisticsService;
import hu.schonherz.training.helpdesk.service.api.service.ConversationService;
import hu.schonherz.training.helpdesk.service.api.vo.ConversationVO;
import hu.schonherz.training.helpdesk.web.BeanConstants;
import hu.schonherz.training.helpdesk.web.security.domain.AgentUser;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.primefaces.event.SelectEvent;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.WeekFields;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

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

    // TODO: Encapsulate agent-related statistics into a type
    private List<LocalDateTime> agentLoginDates;
    private List<ConversationVO> agentConversations;
    private Calendar calendar;
    private Date date;

    @PostConstruct
    public void init() {
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
    }

    public AgentUser getUser() {
        return (AgentUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    public int getPastDayLogin() {
        int dayLoginSize = 0;
        log.info("évv: " + calendar.get(Calendar.WEEK_OF_YEAR) + "");
        for (LocalDateTime login : agentLoginDates) {

            if (((login.getYear()) == calendar.get(Calendar.YEAR)) &&
                    (login.getMonth().getValue() == (calendar.get(Calendar.MONTH) + 1)) &&
                    (login.getDayOfMonth() == calendar.get(Calendar.DAY_OF_MONTH))) {
                dayLoginSize++;
            }
        }
        return dayLoginSize;
    }

    public int getPastWeekLogin() {
        int weekLoginSize = 0;
        for (LocalDateTime login : agentLoginDates) {
            WeekFields weekFields = WeekFields.of(Locale.getDefault());
            int weekNumber = login.toLocalDate().get(weekFields.weekOfWeekBasedYear());
            if (weekNumber == calendar.get(Calendar.WEEK_OF_YEAR)) {
                weekLoginSize++;
            }
        }
        return weekLoginSize;

    }

    public int getPastMonthLogin() {
        int monthLoginSize = 0;
        for (LocalDateTime login : agentLoginDates) {
            if (((login.getYear()) == calendar.get(Calendar.YEAR)) &&
                    (login.getMonth().getValue() == (calendar.get(Calendar.MONTH) + 1))) {
                monthLoginSize++;
            }
        }
        return monthLoginSize;
    }

    public int getPastDayConvs() {
        int dayConvsSize = 0;
        for (ConversationVO conversation : agentConversations) {
            if (((conversation.getCreatedAt().getYear()) == calendar.get(Calendar.YEAR)) &&
                    (conversation.getCreatedAt().getMonth().getValue() == (calendar.get(Calendar.MONTH) + 1)) &&
                    (conversation.getCreatedAt().getDayOfMonth() == calendar.get(Calendar.DAY_OF_MONTH))) {
                dayConvsSize++;
            }
        }
        return dayConvsSize;
    }

    public int getPastWeekConvs() {
        LocalDateTime now = LocalDateTime.now().minusDays(DAYS_IN_WEEK);
        int weekConvsSize = 0;
        for (ConversationVO conversation : agentConversations) {
            if (conversation.getCreatedAt().toLocalDate().equals(now.toLocalDate())) {
                weekConvsSize++;
            }
        }
        return weekConvsSize;
    }

    public int getPastMonthConvs() {
        int monthConvsSize = 0;
        for (ConversationVO conversation : agentConversations) {
            if (((conversation.getCreatedAt().getYear()) == calendar.get(Calendar.YEAR)) &&
                    (conversation.getCreatedAt().getMonth().getValue() == (calendar.get(Calendar.MONTH) + 1))) {
                monthConvsSize++;
            }
        }
        return monthConvsSize;
    }

    public List<LocalDateTime> getThisMonthLogins() {
        LocalDateTime actualDateTime = LocalDateTime.now();
        LocalDateTime firstDayOfThisMonth = actualDateTime.with(TemporalAdjusters.firstDayOfMonth())
                .withHour(0).withMinute(0).withSecond(0);
        return agentLoginDates.stream()
                .filter(e -> e.isAfter(firstDayOfThisMonth))
                .collect(Collectors.toList());
    }

    public void onDateSelect(SelectEvent event) {
        DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        try {
            calendar.setTime(format.parse(format.format(event.getObject())));
        } catch (ParseException e) {
            log.error("Date error" + e);
        }

    }
}
