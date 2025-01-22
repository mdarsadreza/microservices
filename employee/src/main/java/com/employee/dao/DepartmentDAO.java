package com.employee.dao;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.employee.model.Department;

@Repository
public interface DepartmentDAO extends CrudRepository<Department, Long>{
	Optional<Department> findByDepartmentCode(String departmentCode);
}
