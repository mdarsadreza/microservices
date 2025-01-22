package com.gateway.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.Nullable;
import org.springframework.boot.web.reactive.error.ErrorWebExceptionHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Configuration
public class CustomErrorWebExceptionHandler implements ErrorWebExceptionHandler {

    @Override
    public Mono<Void> handle(@Nullable ServerWebExchange exchange,@Nullable Throwable ex) {
        if (exchange == null || ex == null) {
            return Mono.empty();
        }
        CustomErrorResponse errorResponse = new CustomErrorResponse("An error occurred");

        if (ex instanceof JwtAuthenticationException) {
            errorResponse.setMessage(ex.getMessage());
        } else {
            errorResponse.setMessage("Unexpected error occurred");
        }

        exchange.getResponse().setStatusCode(HttpStatus.BAD_REQUEST);
        exchange.getResponse().getHeaders().setContentType(MediaType.APPLICATION_JSON);

        return exchange.getResponse()
                .writeWith(Mono.just(exchange.getResponse().bufferFactory()
                        .wrap(serializeResponse(errorResponse))));
    }

    private byte[] serializeResponse(CustomErrorResponse errorResponse) {
        try {
            return new ObjectMapper().writeValueAsBytes(errorResponse);
        } catch (Exception e) {
            return "{\"message\":\"Error serializing response\"}".getBytes();
        }
    }
}


