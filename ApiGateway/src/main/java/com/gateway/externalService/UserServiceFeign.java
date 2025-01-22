package com.gateway.externalService;

import com.gateway.dto.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class UserServiceFeign {

    @Autowired
    private WebClient.Builder webClientBuilder;

    private final ExecutorService executorService = Executors.newCachedThreadPool();

    public Employee getEmployeeByEmail(String email) throws ExecutionException, InterruptedException {
        return executorService.submit(() -> {
            return webClientBuilder.baseUrl("http://localhost:8082/apis/auth")
                    .build()
                    .get()
                    .uri("/findByEmail/{email}", email)
                    .retrieve()
                    .bodyToMono(Employee.class)
                    .block();
        }).get();
    }

}

