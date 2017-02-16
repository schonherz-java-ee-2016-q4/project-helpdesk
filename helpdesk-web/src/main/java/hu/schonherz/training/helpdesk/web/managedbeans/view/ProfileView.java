package hu.schonherz.training.helpdesk.web.managedbeans.view;

import hu.schonherz.training.helpdesk.web.security.domain.AgentUser;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "profileView")
@ViewScoped
public class ProfileView {
    private AgentUser user;

    @PostConstruct
    public void init() {
        user = (AgentUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    public AgentUser getUser() {
        return user;
    }

}
