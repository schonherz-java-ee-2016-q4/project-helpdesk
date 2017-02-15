package hu.schonherz.training.helpdesk.web.view;

import hu.schonherz.training.helpdesk.web.security.domain.AgentUser;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "profileView")
@ViewScoped
public class ProfileView {
    public AgentUser getUser() {
        return (AgentUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
