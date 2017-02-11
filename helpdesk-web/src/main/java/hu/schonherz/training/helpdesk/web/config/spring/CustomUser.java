package hu.schonherz.training.helpdesk.web.config.spring;

import lombok.Builder;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

@Data
public class CustomUser extends User {
    private static final long serialVersionUID = -9024261681504293335L;
    private String name;
    private String email;
    private String gender;
    private String company;
    private String phone;
    private String picture;

    @Builder
    public CustomUser(final String username, final String password, final boolean enabled, final boolean accountNonExpired,
                      final boolean credentialsNonExpired, final boolean accountNonLocked, final Collection<? extends GrantedAuthority> authorities,
                      final String name, final String email, final String gender,
                      final String company, final String phone, final String picture) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
        this.name = name;
        this.email = email;
        this.gender = gender;
        this.company = company;
        this.phone = phone;
        this.picture = picture;
    }
}
