package hu.schonherz.training.helpdesk.web.mock;

import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class MockedAdminStuff {

    public User findByName(final String username) {
        Set<GrantedAuthority> auths = new HashSet<GrantedAuthority>();
        auths.add(new SimpleGrantedAuthority("ROLE_USER"));
        return new User(username, "123", true, true, true, true, auths);
    }

}
