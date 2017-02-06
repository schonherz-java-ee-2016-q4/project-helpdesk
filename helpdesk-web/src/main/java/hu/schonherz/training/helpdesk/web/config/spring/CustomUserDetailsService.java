package hu.schonherz.training.helpdesk.web.config.spring;

import java.util.HashSet;
import java.util.Set;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import hu.schonherz.training.helpdesk.web.mock.Agent;
import hu.schonherz.training.helpdesk.web.mock.MockedAdminStuff;

@Service("customUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService {
    private static Mapper mapper = new DozerBeanMapper();
    private final MockedAdminStuff userService = new MockedAdminStuff();

    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        return createUser(userService.findByName(username));
    }

    public User createUser(final Agent agent) {
        CustomUser user = new CustomUser(agent.getUsername(), agent.getPassword(), true, true, true, true,
                auths(agent.getRoles()));
        mapper.map(agent, user);
        return user;
    }

    public Set<GrantedAuthority> auths(final String... roles) {
        Set<GrantedAuthority> auths = new HashSet<GrantedAuthority>();
        for (String role : roles) {
            auths.add(new SimpleGrantedAuthority(role));
        }
        return auths;
    }

}
