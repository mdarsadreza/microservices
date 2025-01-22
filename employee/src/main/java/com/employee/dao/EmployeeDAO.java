package com.employee.dao;

import com.employee.model.Department;
import com.employee.model.Employee;
import com.employee.model.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeDAO extends CrudRepository<Employee, Long> {
    Optional<Employee> findByName(String name);
    List<Employee> findByNameIgnoreCase(String name);
    Optional<Employee> findByUsername(String username);
}