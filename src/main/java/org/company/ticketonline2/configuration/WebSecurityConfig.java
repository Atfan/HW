package org.company.ticketonline2.configuration;

import lombok.RequiredArgsConstructor;
import org.company.ticketonline2.service.Endpoints;
import org.company.ticketonline2.service.userdetailsservice.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsServiceImpl userDetailsService;

    @Autowired
    private MyBasicAuthenticationEntryPoint authenticationEntryPoint;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable();

        http.authorizeRequests().mvcMatchers("/", "/login", "/logout").permitAll();

        http.authorizeRequests().mvcMatchers("/userInfo")
                .access("hasAnyRole('ROLE_USER', 'ROLE_ADMIN', 'ROLE_CUSTOMER', 'ROLE_MANAGER', 'ROLE_DEFAULT')");

        http.authorizeRequests().mvcMatchers("/userInfo", "/studentsInGroup", "/studentsGroups/choiceGroup")
                .access("hasAnyRole('ROLE_USER', 'ROLE_ADMIN', 'ROLE_CUSTOMER', 'ROLE_MANAGER')");

        List<Endpoints> endpoints = Endpoints.getEndpointForAdminStuff();

        for (var endpoint : endpoints) {
            http.authorizeRequests().mvcMatchers(endpoint.getEndpoint())
                    .access("hasAnyRole('ROLE_ADMIN', 'ROLE_MANAGER')");
        }

        http.authorizeRequests().mvcMatchers("/events").access("hasAnyRole('ROLE_CUSTOMER', 'ROLE_ADMIN', 'ROLE_MANAGER')");
        http.authorizeRequests().mvcMatchers("/places").access("hasAnyRole('ROLE_CUSTOMER', 'ROLE_ADMIN', 'ROLE_MANAGER')");



        http.authorizeRequests().and().exceptionHandling().authenticationEntryPoint(authenticationEntryPoint);

        http.authorizeRequests().and().formLogin()
                .loginProcessingUrl("/j_spring_security_check")
                .loginPage("/login")
                .defaultSuccessUrl("/userInfo")
                .failureUrl("/login?error=true")
                .usernameParameter("username")
                .passwordParameter("password").and()
                .logout().logoutUrl("/logout").logoutSuccessUrl("/logoutSuccessful");
    }

}
