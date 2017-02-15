package hu.schonherz.training.helpdesk.web.security.domain;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

@Data
public class AgentUser extends User {
    private static final long serialVersionUID = -9024261681504293335L;

    private ProfileDetails profileDetails;

    public AgentUser(final String username,
                     final String password,
                     final boolean enabled,
                     final boolean accountNonExpired,
                     final boolean credentialsNonExpired,
                     final boolean accountNonLocked,
                     final Collection<? extends GrantedAuthority> authorities) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
    }

    public static AgentUserBuilder builder() {
        return new AgentUserBuilder();
    }
}
