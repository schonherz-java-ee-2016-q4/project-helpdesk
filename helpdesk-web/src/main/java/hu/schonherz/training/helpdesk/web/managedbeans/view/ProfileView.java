package hu.schonherz.training.helpdesk.web.managedbeans.view;

import hu.schonherz.training.helpdesk.web.security.domain.AgentUser;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@Slf4j
@Data
@ViewScoped
@ManagedBean(name = "profileView")
public class ProfileView {

    private AgentUser agent;

    @PostConstruct
    public void init() {
        agent = (AgentUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    public AgentUser getAgent() {
        return agent;
    }
}
