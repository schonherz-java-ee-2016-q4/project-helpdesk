package hu.schonherz.training.helpdesk.web.security;

import hu.schonherz.project.admin.service.api.vo.UserRole;
import hu.schonherz.training.helpdesk.web.security.successhandlers.CustomAuthenticationSuccessHandler;
import hu.schonherz.training.helpdesk.web.security.service.AgentUserDetailsService;
import hu.schonherz.training.helpdesk.web.security.successhandlers.CustomLogoutSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private AgentUserDetailsService userDetailsService;

    @Autowired
    private CustomAuthenticationSuccessHandler authenticationSuccessHandler;

    @Autowired
    private CustomLogoutSuccessHandler customLogoutSuccessHandler;

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/agent/**", "/client/**").hasRole(UserRole.AGENT.name())
            .and()
                .formLogin()
                .loginPage("/").failureUrl("/?error")
                .usernameParameter("username")
                .passwordParameter("password")
                .loginProcessingUrl("/login")
                .successHandler(authenticationSuccessHandler)
            .and()
                .logout().logoutSuccessUrl("/?logout")
                .logoutSuccessHandler(customLogoutSuccessHandler)
            .and()
                .csrf().disable();
    }

    public void configureGlobal(final AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }


    @Override
    public void configure(final WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/resources/**");
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
