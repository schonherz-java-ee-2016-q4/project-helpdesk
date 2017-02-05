package hu.schonherz.training.helpdesk.web.config.spring;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

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

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(final String gender) {
        this.gender = gender;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(final String company) {
        this.company = company;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(final String phone) {
        this.phone = phone;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(final String picture) {
        this.picture = picture;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }
}
