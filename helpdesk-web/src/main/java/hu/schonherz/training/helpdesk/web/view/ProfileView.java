package hu.schonherz.training.helpdesk.web.view;

import hu.schonherz.training.helpdesk.web.config.spring.user.CustomUser;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "profileView")
@ViewScoped
public class ProfileView {
    public CustomUser getUser() {
        return (CustomUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
