package com.gateway.exception;
import org.springframework.boot.web.reactive.error.ErrorWebExceptionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;
import reactor.retry.DefaultContext;

@Configuration
public class ErrorConfiguration {

    @Bean
    public ErrorWebExceptionHandler errorWebExceptionHandler() {
        return new CustomErrorWebExceptionHandler();
    }
}