package hu.schonherz.training.helpdesk.web.config.spring;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        http.authorizeRequests()
        .antMatchers("/public/**").permitAll()
        .antMatchers("/secured/**").access("hasRole('ROLE_USER')")
        .and()
            .formLogin().loginPage("/login").failureUrl("/login?error").permitAll()
            .usernameParameter("user").passwordParameter("password")
        .and()
            .logout().logoutSuccessUrl("/login?logout");
    }

    @Override
    public void configure(final WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/resources/**");
    }

}
