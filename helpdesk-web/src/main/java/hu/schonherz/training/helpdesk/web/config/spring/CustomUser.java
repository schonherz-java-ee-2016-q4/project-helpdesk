package hu.schonherz.training.helpdesk.web.config.spring;

import java.util.Collection;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import lombok.Data;

@Data
public class CustomUser extends User {
    private static final long serialVersionUID = -9024261681504293335L;
    private String name;
    private String email;
    private String gender;
    private String company;
    private String phone;
    private String picture;

    public CustomUser(final String username, final String password, final boolean enabled,
            final boolean accountNonExpired, final boolean credentialsNonExpired, final boolean accountNonLocked,
            final Collection<? extends GrantedAuthority> authorities) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
    }
}
