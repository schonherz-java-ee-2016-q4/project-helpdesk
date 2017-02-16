package hu.schonherz.training.helpdesk.web.managedbeans.view;

import hu.schonherz.project.admin.service.api.rpc.LoginDataRetrievalException;
import hu.schonherz.project.admin.service.api.rpc.RpcLoginStatisticsService;
import hu.schonherz.training.helpdesk.web.security.domain.AgentUser;
import lombok.Data;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@ManagedBean(name = "profileView")
@ViewScoped
@Data
public class ProfileView {

    @EJB(lookup = "java:global/admin-ear-0.0.1-SNAPSHOT/admin-service-0.0.1-SNAPSHOT/RpcLoginStatisticsBean")
    private RpcLoginStatisticsService rpcLoginStatisticsService;

    private List<LocalDateTime> allLoginDatas;

    @PostConstruct
    public void createLoginDatas() throws LoginDataRetrievalException {
        List<LocalDateTime> allLoginDates = rpcLoginStatisticsService.getAllLoginsOf(getUser().getUsername());

        //it's needed, because the remote service doesn't sort this collection properly yet!
        Comparator<LocalDateTime> reverseComparator = Comparator.reverseOrder();
        allLoginDates.sort(reverseComparator);
    }

    public List<LocalDateTime> getThisMonthLogins() {
        LocalDateTime actualDateTime = LocalDateTime.now();
        LocalDateTime firstDayOfThisMonth = actualDateTime.with(TemporalAdjusters.firstDayOfMonth()).withHour(0).withMinute(0);
        return allLoginDatas.stream()
            .filter(e -> e.isAfter(firstDayOfThisMonth))
            .collect(Collectors.toList());
    }

    public AgentUser getUser() {
        return (AgentUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

}
