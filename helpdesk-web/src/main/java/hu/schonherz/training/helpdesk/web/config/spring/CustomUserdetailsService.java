package hu.schonherz.training.helpdesk.web.config.spring;

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
            return userService.findByName(username);
        }
        catch (UsernameNotFoundException e) {
            throw new UsernameNotFoundException(e.getMessage());
        }
    }
}

