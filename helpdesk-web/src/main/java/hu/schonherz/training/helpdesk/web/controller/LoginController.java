package hu.schonherz.training.helpdesk.web.controller;

import java.io.IOException;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import lombok.NoArgsConstructor;

@ManagedBean(name = "loginController")
@RequestScoped
@NoArgsConstructor
public class LoginController {

    public String doLogin() throws ServletException, IOException {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            ExternalContext extenalContext = facesContext.getExternalContext();
            RequestDispatcher dispatcher = ((ServletRequest) extenalContext.getRequest()).getRequestDispatcher("/j_spring_security_check");
            dispatcher.forward((ServletRequest) extenalContext.getRequest(), (ServletResponse) extenalContext.getResponse());
            facesContext.responseComplete();
            return null;
    }

}
