package com.project.SpringApp.security;

import com.project.SpringApp.services.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;
import java.util.Collection;

@EnableWebSecurity
@Configuration
public class SecurityConfig {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserDetailsServiceImpl userDetailsServiceImpl;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .authorizeHttpRequests()
                .requestMatchers("/register").permitAll() // Allow access to the register page without authentication
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .defaultSuccessUrl("/", true) // Redirect to the default URL ("/") and allow the destination URL to be overridden based on role
                .successHandler((request, response, authentication) -> {
                    String targetUrl = determineTargetUrl(authentication);
                    response.sendRedirect(targetUrl);
                })
                .permitAll()
                .and().exceptionHandling().accessDeniedPage("/access-denied")
                .and().httpBasic()
                .and().csrf().disable();

        httpSecurity.userDetailsService(userDetailsServiceImpl);


        return httpSecurity.build();
    }

    private String determineTargetUrl(Authentication authentication) {
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        if (authorities.contains(new SimpleGrantedAuthority("TEACHER"))) {
            return "/teacher/dashboard";
        } else {
            return "/student/dashboard";
        }
    }


}

