package hu.schonherz.training.helpdesk.web.config.spring;

import hu.schonherz.training.helpdesk.service.api.service.LoginService;
import hu.schonherz.training.helpdesk.service.api.vo.LoginVO;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;


@Component
public class CustomAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    @EJB
    private LoginService loginService;

    @Override
    public void onAuthenticationSuccess(final HttpServletRequest request, final HttpServletResponse response,
            final Authentication authentication) throws IOException, ServletException {
        LoginVO loginVO = new LoginVO();
        loginVO.setAgentId(1);
        loginVO.setLoginDate(LocalDateTime.now());
        loginService.save(loginVO);
        super.setDefaultTargetUrl("/agent/profile");
        super.onAuthenticationSuccess(request, response, authentication);
    }

}
