package com.gateway.jwt;

import com.gateway.dto.Employee;
import com.gateway.externalService.UserServiceFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.Collections;
import java.util.concurrent.ExecutionException;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserServiceFeign userServiceClient;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Employee employee = null;
        try {
            employee = userServiceClient.getEmployeeByEmail(username);
        } catch (ExecutionException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        if (employee == null) {
            throw new UsernameNotFoundException("User not found");
        }
        String role = "ROLE_" + employee.getRole().getName().name();
        return new User(
                employee.getUsername(),
                "N/A",
                Collections.singletonList(new SimpleGrantedAuthority(role))
        );
    }
}
