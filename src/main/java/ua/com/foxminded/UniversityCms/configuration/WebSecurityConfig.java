package ua.com.foxminded.UniversityCms.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import ua.com.foxminded.UniversityCms.service.Endpoints;
import ua.com.foxminded.UniversityCms.service.userdetailsservice.UserDetailsServiceImpl;

import java.util.ArrayList;
import java.util.List;

import static ua.com.foxminded.UniversityCms.service.Endpoints.*;

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
