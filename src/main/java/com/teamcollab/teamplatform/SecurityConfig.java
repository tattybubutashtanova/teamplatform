package com.teamcollab.teamplatform.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())  // Disable CSRF for H2 Console
                .headers(headers -> headers.frameOptions(frame -> frame.disable())) // Allow H2 Console in frames
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/h2-console/**").permitAll()  // Allow access to H2 Console
                        .anyRequest().permitAll() // Allow all other requests (only for development)
                );
        return http.build();
    }
}
