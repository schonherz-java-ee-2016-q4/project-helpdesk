package hu.schonherz.training.helpdesk.web.security.service;

import hu.schonherz.project.admin.service.api.rpc.FailedRpcLoginAttemptException;
import hu.schonherz.project.admin.service.api.rpc.RpcLoginServiceRemote;
import hu.schonherz.project.admin.service.api.vo.UserData;
import hu.schonherz.project.admin.service.api.vo.UserRole;
import hu.schonherz.training.helpdesk.web.security.domain.AgentUser;
import hu.schonherz.training.helpdesk.web.security.domain.ProfileDetails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.ejb.EJB;
import java.util.HashSet;
import java.util.Set;

@Slf4j
@Service("customUserDetailsService")
public class AgentUserDetailsService implements UserDetailsService {
    private static final String ROLE_PREFIX = "ROLE_";

    @EJB(mappedName = "java:global/admin-ear-0.0.1-SNAPSHOT/admin-service-0.0.1-SNAPSHOT/RpcLoginServiceBean!"
            + "hu.schonherz.project.admin.service.api.rpc.RpcLoginServiceRemote")
    private RpcLoginServiceRemote rpcLoginServiceRemote;

    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        UserData userData = null;

        try {
            userData = rpcLoginServiceRemote.rpcLogin(username);
        } catch (FailedRpcLoginAttemptException e) {
            log.warn("Authentication failed. Credentials are invalid!", e);
            throw new UsernameNotFoundException("Invalid username " + username, e);
        }

        AgentUser user = AgentUser.builder()
                .username(userData.getUsername())
                .password(userData.getPassword())
                .enabled(true)
                .accountNonExpired(true)
                .credentialsNonExpired(true)
                .accountNonLocked(true)
                .authorities(setAuthorities(userData.getUserRole()))
                .profileDetails(
                        ProfileDetails.builder()
                                .id(userData.getId())
                                .email(userData.getEmail())
                                //dummy code starts here
                                .name("Bruce Wayne")
                                .gender("male")
                                .company("Wayne Enterprises, Inc")
                                .phone("+36-30-1112367")
                                .picture("https://pbs.twimg.com/profile_images/649259478332784640/7Pjcfx_v_reasonably_small.jpg")
                                //dummy code ends here
                                .build()
                )
                .build();

        log.info("Helpdesk agent with username {} successfully authenticated.", username);

        return user;
    }

    @SuppressWarnings("PMD")
    private static Set<GrantedAuthority> setAuthorities(final UserRole userRole) {
        Set<GrantedAuthority> authorities = new HashSet<>();

        switch (userRole) {
            case ADMIN:
                authorities.add(new SimpleGrantedAuthority(ROLE_PREFIX + UserRole.ADMIN.name()));
            case COMPANY_ADMIN:
                authorities.add(new SimpleGrantedAuthority(ROLE_PREFIX + UserRole.COMPANY_ADMIN.name()));
            case AGENT:
                authorities.add(new SimpleGrantedAuthority(ROLE_PREFIX + UserRole.AGENT.name()));
                break;
            default:
                log.warn("User has a role '{}' that is not supported yet!", userRole.toString());
        }

        return authorities;
    }

}
