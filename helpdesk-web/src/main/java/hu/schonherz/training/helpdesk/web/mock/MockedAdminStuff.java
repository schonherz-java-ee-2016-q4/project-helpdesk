package hu.schonherz.training.helpdesk.web.mock;

import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class MockedAdminStuff {

    public User findByName(final String username) {
        if ("admin".equals(username)) {
            Set<GrantedAuthority> auths = new HashSet<GrantedAuthority>();
            auths.add(new SimpleGrantedAuthority("ROLE_USER"));
            return new User(username, "admin", true, true, true, true, auths);
        } else {
            throw new UsernameNotFoundException(username);
        }
    }

}
