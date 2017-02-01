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
        .antMatchers("/login/**").permitAll()
        .antMatchers("/secured/**", "/agent/**", "/client").hasRole("USER")
                .and().formLogin().loginPage("/public/login").failureUrl("/public/login?error").permitAll().
                usernameParameter("username").passwordParameter("password")
            .and()
                .logout().logoutSuccessUrl("/login?logout");
   }

    @Override
    public void configure(final WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/resources/**");
    }

}
