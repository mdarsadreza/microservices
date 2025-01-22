package com.gateway.jwt;

import com.gateway.exception.CustomRoleAccessDenied;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfigurer {

    @Autowired
    private JWTAuthenticateFilter jwtAuthenticateFilter;

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        http
                .csrf(ServerHttpSecurity.CsrfSpec::disable)
                .authorizeExchange(exchanges -> exchanges
                        .pathMatchers(HttpMethod.GET,
                                "/v3/api-docs/**",
                                "/swagger-ui/**",
                                "/employee-service/v3/api-docs"
                        ).permitAll()
                        .pathMatchers(HttpMethod.POST,"/apis/auth/login").permitAll()
                        .pathMatchers("/apis/role/**").hasRole("SUPER_USER")
                        .anyExchange().authenticated()
                )
                .exceptionHandling(exceptionHandlingSpec ->
                        exceptionHandlingSpec.accessDeniedHandler(new CustomRoleAccessDenied())
                )
                .addFilterAt(jwtAuthenticateFilter, SecurityWebFiltersOrder.AUTHENTICATION);

        return http.build();
    }
}


