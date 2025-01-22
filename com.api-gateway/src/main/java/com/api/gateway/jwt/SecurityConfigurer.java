package com.api.gateway.jwt;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfigurer {

    private final JWTAuthenticateFilter jwtAuthenticateFilter;

    public SecurityConfigurer(JWTAuthenticateFilter jwtAuthenticateFilter) {
        this.jwtAuthenticateFilter = jwtAuthenticateFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())  // Disable CSRF for simplicity
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.POST, "/apis/auth/login").permitAll()  // Allow login endpoint for all users
                        .anyRequest().authenticated()  // Authenticate other requests
                )
                .addFilterBefore(jwtAuthenticateFilter, UsernamePasswordAuthenticationFilter.class);  // Add the JWT authentication filter before UsernamePasswordAuthenticationWebFilter

        return http.build();
    }
}
