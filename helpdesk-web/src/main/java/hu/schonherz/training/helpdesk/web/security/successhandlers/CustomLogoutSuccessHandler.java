package hu.schonherz.training.helpdesk.web.security.successhandlers;

import hu.schonherz.project.admin.service.api.rpc.FailedRpcLogoutException;
import hu.schonherz.project.admin.service.api.rpc.RpcLoginServiceRemote;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Component
public class CustomLogoutSuccessHandler extends SimpleUrlLogoutSuccessHandler {
    @EJB(mappedName = "java:global/admin-ear-0.0.1-SNAPSHOT/admin-service-0.0.1-SNAPSHOT/RpcLoginServiceBean!"
        + "hu.schonherz.project.admin.service.api.rpc.RpcLoginServiceRemote")
    private RpcLoginServiceRemote rpcLoginServiceRemote;

    @Override
    public void onLogoutSuccess(final HttpServletRequest request, final HttpServletResponse response,
                                final Authentication authentication) throws IOException, ServletException {
        User user = (User) authentication.getPrincipal();
        try {
            rpcLoginServiceRemote.rpcLogout(user.getUsername());
        } catch (FailedRpcLogoutException e) {
            log.error("User {} couldn't logout at the admin side.", user.getUsername(), e);
        }
        super.onLogoutSuccess(request, response, authentication);
    }
}
