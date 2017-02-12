package hu.schonherz.training.helpdesk.web.config.spring.user;

import org.springframework.security.core.GrantedAuthority;

import java.util.Set;

public final class CustomUserBuilder {
    private String username;
    private String password;
    private boolean enabled;
    private boolean accountNonExpired;
    private boolean accountNonLocked;
    private boolean credentialsNonExpired;
    private Set<GrantedAuthority> authorities;

    private ProfileDetails profileDetails;

    public CustomUserBuilder username(final String username) {
        this.username = username;
        return this;
    }

    public CustomUserBuilder password(final String password) {
        this.password = password;
        return this;
    }

    public CustomUserBuilder enabled(final boolean enabled) {
        this.enabled = enabled;
        return this;
    }

    public CustomUserBuilder accountNonExpired(final boolean accountNonExpired) {
        this.accountNonExpired = accountNonExpired;
        return this;
    }

    public CustomUserBuilder accountNonLocked(final boolean accountNonLocked) {
        this.accountNonLocked = accountNonLocked;
        return this;
    }

    public CustomUserBuilder credentialsNonExpired(final boolean credentialsNonExpired) {
        this.credentialsNonExpired = credentialsNonExpired;
        return this;
    }

    public CustomUserBuilder authorities(final Set<GrantedAuthority> authorities) {
        this.authorities = authorities;
        return this;
    }

    public CustomUserBuilder profileDetails(final ProfileDetails profileDetails) {
        this.profileDetails = profileDetails;
        return this;
    }

    public CustomUser build() {
        CustomUser customUser = new CustomUser(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
        customUser.setProfileDetails(this.profileDetails);
        return customUser;
    }
}
