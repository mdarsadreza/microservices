package com.employee.service.impl;

import com.employee.dao.EmployeeDAO;
import com.employee.exception.DepartmentNotFoundException;
import com.employee.model.Department;
import com.employee.model.Employee;
import com.employee.model.Role;
import com.employee.service.EmployeeService;
import com.employee.utils.EmployeeConverter;
import com.employee.utils.Request;
import com.employee.utils.Response;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeDAO empRepository;

    @Autowired
    private EmployeeConverter convert;

    @Override
    public Response save(Request request) {
        Employee emp= convert.convertRequestToObj(request);
        empRepository.save(emp);
        return convert.convertObjToResonse(emp);
    }

    @Override
    public ResponseEntity<Response> findById(Long empId) {
        Optional<Employee> empOptional = empRepository.findById(empId);
        if(empOptional.isEmpty()) {
            throw new DepartmentNotFoundException("Employee not found, please add it first.");
        }
        Employee emp = empOptional.get();
        if (emp.getDepartment() != null) {
            Hibernate.initialize(emp.getDepartment());
        }
        if (emp.getRole() != null) {
            Hibernate.initialize(emp.getRole());
        }
        Response response = convert.convertObjToResonse(emp);
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<List<Response>> findByName(String name) {
        List<Employee> empList = empRepository.findByNameIgnoreCase(name);
        List<Response> responseList = empList.stream()
                .map(emp -> convert.convertObjToResonse(emp))
                .collect(Collectors.toList());
        return ResponseEntity.ok(responseList);
    }

    @Override
    public ResponseEntity<Response> editEmployeeDetails(Long empId, Request request) {
        Optional<Employee> emp = empRepository.findById(empId);
        if(emp.isEmpty()) {
            throw new DepartmentNotFoundException("Employee not found, please add it first.");
        }
        Employee employee = emp.get();
        if (request.getName() != null && !request.getName().isEmpty()) {
            employee.setName(request.getName());
        }

        if (request.getUsername() != null && !request.getUsername().isEmpty()) {
            employee.setUsername(request.getUsername());
        }

        if (request.getPosition() != null && !request.getPosition().isEmpty()) {
            employee.setPosition(request.getPosition());
        }
        empRepository.save(employee);
        Response response = convert.convertObjToResonse(employee);
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<String> deleteById(Long empId) {
        Optional<Employee> emp = empRepository.findById(empId);

        if (emp.isEmpty()) {
            throw new DepartmentNotFoundException("Employee not found.");
        }
        empRepository.delete(emp.get());
        return ResponseEntity.status(HttpStatus.OK).body("Employee deleted successfully.");
    }

    @Override
    public Employee getEmployeeByEmail(String username) {
        Optional<Employee> empOptional = empRepository.findByUsername(username);
        if(empOptional.isEmpty()) {
            throw new DepartmentNotFoundException("Employee not found, please add it first.");
        }
        Employee emp = empOptional.get();
        Role role = new Role();
        role.setName(emp.getRole().getName());
        role.setId(emp.getRole().getId());
        Department department = new Department();
        department.setDepartmentName(emp.getDepartment().getDepartmentName());
        department.setDepartmentAddress(emp.getDepartment().getDepartmentAddress());
        department.setDepartmentCode(emp.getDepartment().getDepartmentCode());
        department.setDepartmentId(emp.getDepartment().getDepartmentId());
        Employee employee = new Employee();
        employee.setId(emp.getId());
        employee.setPassword(emp.getPassword());
        employee.setPosition(emp.getPosition());
        employee.setDepartment(department);
        employee.setRole(role);
        employee.setUsername(emp.getUsername());
        employee.setName(emp.getName());
        return employee;
    }
}
