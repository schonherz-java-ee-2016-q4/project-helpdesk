package hu.schonherz.training.helpdesk.web.managedbeans.session;

import lombok.extern.slf4j.Slf4j;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

@Slf4j
@SessionScoped
@ManagedBean(name = "languageBean")
public class LanguageBean {
    private static final String MESSAGES_FILE = "helpdesk.Messages";
    private Locale locale;
    private ResourceBundle localeMessages;

    @PostConstruct
    public void init() {
        locale = FacesContext.getCurrentInstance().getExternalContext().getRequestLocale();
        updateLocaleMessages();
    }

    public void setLanguage(final String language) {
        if (!locale.getLanguage().equals(language)) {
            locale = new Locale(language);
            updateLocaleMessages();
            FacesContext.getCurrentInstance().getViewRoot().setLocale(locale);
            ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
            log.info("language chaner invoked");
            try {
                externalContext.redirect(((HttpServletRequest) externalContext.getRequest()).getRequestURI());
            } catch (IOException e) {
                log.error("Failed to change language.", e);
            }
        }
    }

    private void updateLocaleMessages() {
        localeMessages = ResourceBundle.getBundle(MESSAGES_FILE, locale);
    }

    public Locale getLocale() {
        return locale;
    }

    public String localize(final String key) {
        return localeMessages.getString(key);
    }

    public String changeLanguage() {
        return "changed";
    }
}
