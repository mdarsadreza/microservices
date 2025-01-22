package com.gateway.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @ExceptionHandler(JwtAuthenticationException.class)
    public Mono<ResponseEntity<String>> handleJwtAuthenticationException(JwtAuthenticationException ex) {
        return Mono.just(ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage()));
    }

    @ExceptionHandler(ResponseStatusException.class)
    public Mono<Void> handleNotFound(ResponseStatusException ex, ServerWebExchange exchange) {
        if (ex.getStatusCode() == HttpStatus.NOT_FOUND) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("message", "URL not found: " + exchange.getRequest().getPath());
            String jsonResponse;
            try {
                jsonResponse = objectMapper.writeValueAsString(errorResponse);
            } catch (Exception e) {
                jsonResponse = "{\"message\": \"An error occurred while processing the error response.\"}";
            }
            exchange.getResponse().setStatusCode(HttpStatus.NOT_FOUND);
            exchange.getResponse().getHeaders().setContentType(MediaType.APPLICATION_JSON);
            return exchange.getResponse().writeWith(
                    Mono.just(exchange.getResponse()
                            .bufferFactory()
                            .wrap(jsonResponse.getBytes()))
            );
        }
        return Mono.error(ex);
    }
}


