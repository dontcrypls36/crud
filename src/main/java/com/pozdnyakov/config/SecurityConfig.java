package com.pozdnyakov.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
@EnableWebSecurity
@ComponentScan({"com.pozdnyakov"})
public class SecurityConfig extends WebSecurityConfigurerAdapter{

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/spring_security_check")
                .defaultSuccessUrl("/default")
                .failureUrl("/login?error")
                .usernameParameter("j_username")
                .passwordParameter("j_password")
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login?logout")
                .invalidateHttpSession(true);

        http.authorizeRequests().antMatchers("/addperson", "/menu").hasAuthority("ADMIN")
                .and().csrf()
                .and().exceptionHandling().accessDeniedPage("/403");
    }

}
