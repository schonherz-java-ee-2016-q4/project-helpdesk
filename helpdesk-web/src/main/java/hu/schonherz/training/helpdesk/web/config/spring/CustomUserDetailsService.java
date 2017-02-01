package hu.schonherz.training.helpdesk.web.config.spring;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import hu.schonherz.training.helpdesk.web.mock.MockedAdminStuff;

@Service("customUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService {

    private final MockedAdminStuff userService = new MockedAdminStuff();

    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        return userService.findByName(username);
    }
}
