package org.company.TicketOnline2.configuration;

import lombok.RequiredArgsConstructor;
import org.company.TicketOnline2.service.Endpoints;
import org.company.TicketOnline2.service.userdetailsservice.UserDetailsServiceImpl;
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
                .access("hasAnyRole('ROLE_USER', 'ROLE_ADMIN', 'ROLE_STUDENT', 'ROLE_TEACHER'," +
                        "        'ROLE_STUFF','ROLE_DEFAULT')");

        http.authorizeRequests().mvcMatchers("/userInfo", "/studentsInGroup", "/studentsGroups/choiceGroup")
                .access("hasAnyRole('ROLE_USER', 'ROLE_ADMIN', 'ROLE_STUDENT', 'ROLE_TEACHER', 'ROLE_STUFF')");

        List<Endpoints> endpoints = Endpoints.getEndpointForAdminStuff();

        for (var endpoint : endpoints) {
            http.authorizeRequests().mvcMatchers(endpoint.getEndpoint())
                    .access("hasAnyRole('ROLE_ADMIN', 'ROLE_STUFF')");
        }

        http.authorizeRequests().mvcMatchers("/students/get", "/groups/get").access("hasAnyRole('ROLE_STUDENT', 'ROLE_ADMIN', 'ROLE_STUFF')");
        http.authorizeRequests().mvcMatchers("/teachers/get").access("hasAnyRole('ROLE_TEACHER', 'ROLE_ADMIN', 'ROLE_STUFF')");
        http.authorizeRequests().mvcMatchers("/subjects/get").access("hasAnyRole('ROLE_TEACHER', 'ROLE_ADMIN', 'ROLE_STUDENT', 'ROLE_STUFF')");
        http.authorizeRequests().mvcMatchers("/teachersSubjects", "/teacherSubjects/choiceTeacher").access("hasAnyRole('ROLE_TEACHER')");

        http.authorizeRequests().mvcMatchers("/briefsubjects").access("hasAnyRole('ROLE_DEFAULT')");

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