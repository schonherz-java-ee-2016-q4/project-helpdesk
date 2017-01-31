package hu.schonherz.training.helpdesk.web.config.spring;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import hu.schonherz.training.helpdesk.web.mock.MockedAdminStuff;

@Service("customUserDetailsService")
public class CustomUserdetailsService implements UserDetailsService {

    private MockedAdminStuff userService = new MockedAdminStuff();

    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        try {
            if (userService.findByName(username) == null) {
                throw new UsernameNotFoundException(username);
            }
            List<GrantedAuthority> authorities = buildUserAuthority(userService.getUser().getRoles());
            return new User(userService.findByName(username).getUsername(), userService.findByName(username).getPassword(), true, true, true, true, authorities);
        } catch (Exception e) {
            throw new UsernameNotFoundException(e.getMessage());
        }
    }

    private List<GrantedAuthority> buildUserAuthority(final String... roles) {
        Set<GrantedAuthority> setAuths = new HashSet<GrantedAuthority>();
        for (String role : roles) {
            setAuths.add(new SimpleGrantedAuthority(role));
        }
        return new ArrayList<GrantedAuthority>(setAuths);
    }

}
