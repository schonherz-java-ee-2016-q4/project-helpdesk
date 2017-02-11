package hu.schonherz.training.helpdesk.web.config.spring;

import hu.schonherz.project.admin.service.api.rpc.FailedRpcLoginAttemptException;
import hu.schonherz.project.admin.service.api.rpc.RpcLoginServiceRemote;
import hu.schonherz.project.admin.service.api.vo.UserData;
import hu.schonherz.project.admin.service.api.vo.UserRole;
import hu.schonherz.training.helpdesk.web.mock.MockedAdminStuff;
import lombok.extern.slf4j.Slf4j;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.ejb.EJB;
import java.util.HashSet;
import java.util.Set;

@Service("customUserDetailsService")
@Slf4j
public class CustomUserDetailsService implements UserDetailsService {
    private static Mapper mapper = new DozerBeanMapper();

    @EJB(mappedName = "java:global/admin-ear-0.0.1-SNAPSHOT/admin-service-0.0.1-SNAPSHOT/RpcLoginServiceBean!"
        + "hu.schonherz.project.admin.service.api.rpc.RpcLoginServiceRemote")
    private RpcLoginServiceRemote rpcLoginServiceRemote;


    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        UserData userData = null;
        try {
            userData = rpcLoginServiceRemote.rpcLogin(username);
        } catch (FailedRpcLoginAttemptException e) {
            log.error(e.toString());
            throw new UsernameNotFoundException(username + "not found");
        }

        CustomUser user = CustomUser.builder()
            .username(userData.getUsername())
            .password(userData.getPassword())
            .enabled(true)
            .accountNonExpired(true)
            .credentialsNonExpired(true)
            .accountNonLocked(true)
            .authorities(auths(userData.getUserRole()))
            //here the CustomUser part starts.
            .email(userData.getEmail())
            .build();


        log.info("User in CustomUserDetailsService: " + user.toString());
        log.info("Username: " + user.getUsername() + " User roles: " + user.getAuthorities().toString()
            + " User email: " + user.getEmail());

        mapper.map(MockedAdminStuff.getDummy(), user);
        return user;
    }

    private static Set<GrantedAuthority> auths(final UserRole userRole) {
        Set<GrantedAuthority> auths = new HashSet<>();

        final String rolePrefix = "ROLE_";
        try {
            switch (userRole) {
                case ADMIN:
                    auths.add(new SimpleGrantedAuthority(rolePrefix + UserRole.ADMIN.name()));
                case COMPANY_ADMIN:
                    auths.add(new SimpleGrantedAuthority(rolePrefix + UserRole.COMPANY_ADMIN.name()));
                case AGENT:
                    auths.add(new SimpleGrantedAuthority(rolePrefix + UserRole.AGENT.name()));
                    break;
                default:
                    //This will also prevent not supported roles to log in.
                    throw new RoleNotFoundException("User has a role \"" + userRole.toString() + "\" that is not supported yet!");
            }
        } catch (RoleNotFoundException e) {
            log.error(e.toString());
        }


        return auths;
    }

}
