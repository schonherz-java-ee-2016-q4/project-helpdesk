package hu.schonherz.training.helpdesk.web.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Statistic {
    private int numberOfDailyLogins;
    private int numberOfWeeklyLogins;
    private int numberOfMonthlyLogins;
    private int numberOfDailyConversations;
    private int numberOfWeeklyConversations;
    private int numberOfMonthlyConversations;
    private int numberOfDailyTickets;
    private int numberOfWeeklyTickets;
    private int numberOfMonthlyTickets;
}
