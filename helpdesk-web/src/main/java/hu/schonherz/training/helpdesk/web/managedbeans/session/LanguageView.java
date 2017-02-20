package hu.schonherz.training.helpdesk.web.managedbeans.session;

import lombok.extern.slf4j.Slf4j;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Locale;

@ManagedBean(name = "languageView")
@SessionScoped
@Slf4j
public class LanguageView {
    private static String locale = Locale.getDefault().getDisplayLanguage();

    public void setLocale(final String locale1) {
        locale = locale1;

        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        try {
            ec.redirect(((HttpServletRequest) ec.getRequest()).getRequestURI());
        } catch (IOException e) {
            log.error("Failed to redirect to same page during language change.", e);
        }
    }

    public String getLocale() {
        return locale;
    }

    public String changeLanguage() {
        return "changed";
    }
}
