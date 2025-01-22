package com.api.gateway.externalService;

import com.api.gateway.dto.Employee;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "employee-service", url = "http://localhost:8082/api/auth")
public interface UserServiceClient {

    @GetMapping("findByEmail/{email}")
    Employee getEmployeeByEmail(@PathVariable("email") String email);
}

