package hu.schonherz.training.helpdesk.web.managedbeans.session;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

@ManagedBean(name = "languageBean")
@SessionScoped
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
        }
    }

    private void updateLocaleMessages() {
        localeMessages = ResourceBundle.getBundle(MESSAGES_FILE, locale);
    }

    public Locale getLocale() {
        return locale;
    }

    public ResourceBundle getLocaleMessages() {
        return localeMessages;
    }

    public String localize(String key, String... params) {
        String pattern = getLocalizedPattern(key);
        return format(pattern, params);
    }

    private String getLocalizedPattern(String key) {
        return getLocaleMessages().getString(key);
    }

    private String format(String pattern, String... params) {
        MessageFormat formatter = new MessageFormat(pattern, getLocale());
        return formatter.format(params);
    }

    public void setLocale(final String locale) {
        this.locale = new Locale(locale);
    }

    public String changeLanguage() {
        return "changed";
    }
}
