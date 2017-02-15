package hu.schonherz.training.helpdesk.web.security.domain;

import org.springframework.security.core.GrantedAuthority;

import java.util.Set;

public final class AgentUserBuilder {
    private String username;
    private String password;
    private boolean enabled;
    private boolean accountNonExpired;
    private boolean accountNonLocked;
    private boolean credentialsNonExpired;
    private Set<GrantedAuthority> authorities;

    private ProfileDetails profileDetails;

    public AgentUserBuilder username(final String username) {
        this.username = username;
        return this;
    }

    public AgentUserBuilder password(final String password) {
        this.password = password;
        return this;
    }

    public AgentUserBuilder enabled(final boolean enabled) {
        this.enabled = enabled;
        return this;
    }

    public AgentUserBuilder accountNonExpired(final boolean accountNonExpired) {
        this.accountNonExpired = accountNonExpired;
        return this;
    }

    public AgentUserBuilder accountNonLocked(final boolean accountNonLocked) {
        this.accountNonLocked = accountNonLocked;
        return this;
    }

    public AgentUserBuilder credentialsNonExpired(final boolean credentialsNonExpired) {
        this.credentialsNonExpired = credentialsNonExpired;
        return this;
    }

    public AgentUserBuilder authorities(final Set<GrantedAuthority> authorities) {
        this.authorities = authorities;
        return this;
    }

    public AgentUserBuilder profileDetails(final ProfileDetails profileDetails) {
        this.profileDetails = profileDetails;
        return this;
    }

    public AgentUser build() {
        final AgentUser customUser = new AgentUser(
            username,
            password,
            enabled,
            accountNonExpired,
            credentialsNonExpired,
            accountNonLocked,
            authorities);
        customUser.setProfileDetails(this.profileDetails);

        return customUser;
    }
}
