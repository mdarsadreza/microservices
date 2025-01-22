package com.employee.controller;

import com.employee.jwt.AuthService;
import com.employee.jwt.ResponseToken;
import com.employee.model.Employee;
import com.employee.service.DepartmentService;
import com.employee.service.EmployeeService;
import com.employee.utils.Request;
import com.employee.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/apis/auth")
public class EmployeeController {

    @Autowired
    private EmployeeService empService;

    @Autowired
    private AuthService authService;

    @PostMapping("/save")
    public Response save(@RequestBody Request req) {
        return empService.save(req);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Response> findById(@PathVariable("id") Long departmentId) {
        return empService.findById(departmentId);
    }

    @GetMapping("/findByName/{name}")
    public ResponseEntity<List<Response>> findById(@PathVariable("name") String name) throws Exception {
        return empService.findByName(name);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Response> edit(@PathVariable("id") long id, @RequestBody Request request) throws Exception {
        return empService.editEmployeeDetails(id, request);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") long id) {
        return empService.deleteById(id);
    }

    @GetMapping("/findByEmail/{email}")
    public Employee getEmployeeByEmail(@PathVariable("email") String email) {
        Employee employee = empService.getEmployeeByEmail(email);
        return employee;
    }

    @PostMapping("/login")
    public ResponseToken login(@RequestBody Request req) throws Exception {
        Employee employee = empService.getEmployeeByEmail(req.getUsername());
        return authService.authenticateAndGenerateToken(req.getUsername(), req.getPassword(), employee);
    }

    @PostMapping("/refresh-token")
    public ResponseToken refreshToken(@RequestBody String refreshToken) {
        try {
            return authService.refreshAccessToken(refreshToken);
        } catch (Exception e) {
            return new ResponseToken("Error refreshing token: " + e.getMessage(), null, null);
        }
    }
}
