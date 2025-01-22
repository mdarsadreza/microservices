package com.employee.service;

import com.employee.model.Employee;
import com.employee.utils.Request;
import com.employee.utils.Response;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface EmployeeService {

    public Response save(Request emp);
    public ResponseEntity<Response> findById(Long empId);
    public ResponseEntity<List<Response>> findByName(String name);
    public ResponseEntity<Response> editEmployeeDetails(Long empId, Request emp);
    public ResponseEntity<String> deleteById(Long empId);

    Employee getEmployeeByEmail(String username);
}
