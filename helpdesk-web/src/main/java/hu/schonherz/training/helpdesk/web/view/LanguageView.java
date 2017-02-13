package hu.schonherz.training.helpdesk.web.view;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.util.Locale;

@ManagedBean(name = "languageView")
@ViewScoped
public class LanguageView {
    private static String locale = Locale.getDefault().getDisplayLanguage();

    public void setLocale(final String locale1) {
        locale = locale1;
    }

    public String getLocale() {
        return locale;
    }

    public String changeLanguage() {
        return "changed";
    }
}
