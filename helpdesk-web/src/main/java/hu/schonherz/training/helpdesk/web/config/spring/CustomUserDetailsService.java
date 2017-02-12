package hu.schonherz.training.helpdesk.web.config.spring;

import hu.schonherz.project.admin.service.api.rpc.FailedRpcLoginAttemptException;
import hu.schonherz.project.admin.service.api.rpc.RpcLoginServiceRemote;
import hu.schonherz.project.admin.service.api.vo.UserData;
import hu.schonherz.project.admin.service.api.vo.UserRole;
import hu.schonherz.training.helpdesk.web.config.spring.user.CustomUser;
import hu.schonherz.training.helpdesk.web.config.spring.user.ProfileDetails;
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
            throw new UsernameNotFoundException(username + "not found", e);
        }

        CustomUser user = CustomUser.builder()
            .username(userData.getUsername())
            .password(userData.getPassword())
            .enabled(true)
            .accountNonExpired(true)
            .credentialsNonExpired(true)
            .accountNonLocked(true)
            .authorities(auths(userData.getUserRole()))
            .profileDetails(
                ProfileDetails.builder()
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


        log.info("User in CustomUserDetailsService: " + user.toString());
        log.info("Username: " + user.getUsername() + " User roles: " + user.getAuthorities().toString()
            + " User email: " + user.getProfileDetails().getEmail());

        return user;
    }

    @SuppressWarnings("PMD")
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
