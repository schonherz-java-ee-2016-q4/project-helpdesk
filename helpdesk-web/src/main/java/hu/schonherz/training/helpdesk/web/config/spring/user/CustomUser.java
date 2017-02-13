package hu.schonherz.training.helpdesk.web.config.spring.user;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

@Data
public class CustomUser extends User {
    private static final long serialVersionUID = -9024261681504293335L;
    private ProfileDetails profileDetails;

    //Visibility intetntionally left package private, because the CustomUserBuilder class uses it
    CustomUser(final String username, final String password, final boolean enabled,
                      final boolean accountNonExpired, final boolean credentialsNonExpired,
                      final boolean accountNonLocked, final Collection<? extends GrantedAuthority> authorities) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
    }

    public static CustomUserBuilder builder() {
        return new CustomUserBuilder();
    }
}
