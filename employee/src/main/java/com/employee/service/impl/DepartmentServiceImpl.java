package com.employee.service.impl;

import java.util.Optional;

import com.employee.exception.RoleNotFoundException;
import com.employee.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.employee.dao.DepartmentDAO;
import com.employee.exception.DepartmentNotFoundException;
import com.employee.model.Department;
import com.employee.service.DepartmentService;
import com.employee.utils.DepartmentConverter;
import com.employee.utils.Request;
import com.employee.utils.Response;

import org.springframework.http.ResponseEntity;

@Service
public class DepartmentServiceImpl implements DepartmentService {
	
	@Autowired
	private DepartmentDAO deptRepository;
	
	@Autowired
	private DepartmentConverter convert;

	@Override
	public Response save(Request request) {
		Department dept= convert.convertRequestToObj(request);
		deptRepository.save(dept);
		 Response response = convert.convertObjToResonse(dept);
		return response;
	}

	@Override
	public ResponseEntity<Response> findById(Long deptId) {
		 Optional<Department> department = deptRepository.findById(deptId);
		 if(department.isEmpty()) {
			 throw new DepartmentNotFoundException("Department not found, please add it first.");
		 }
		 Response response = convert.convertObjToResonse(department.get());
		 return ResponseEntity.ok(response);
	}
	
	@Override
	public ResponseEntity<Response> findDepartmentCode(String deptName) throws Exception {
		 Optional<Department> department = deptRepository.findByDepartmentCode(deptName);
		 if(department.isEmpty()) {
			 throw new DepartmentNotFoundException("Please enter correct department code");
		 }

		 Response response = convert.convertObjToResonse(department.get());
		 return ResponseEntity.ok(response);
	}

	@Override
	public ResponseEntity<Response> editDepartment(Long deptId, Request request) {
		Optional<Department> department = deptRepository.findById(deptId);
		if(department.isEmpty()) {
			throw new DepartmentNotFoundException("Department not found, please add it first.");
		}
		Department dept = department.get();
		if (request.getCode() != null && !request.getCode().isEmpty()) {
			dept.setDepartmentCode(request.getCode());
		}

		if (request.getAddress() != null && !request.getAddress().isEmpty()) {
			dept.setDepartmentAddress(request.getAddress());
		}

		if (request.getName() != null && !request.getName().isEmpty()) {
			dept.setDepartmentName(request.getName());
		}
		deptRepository.save(dept);
		Response response = convert.convertObjToResonse(dept);
		return ResponseEntity.ok(response);
	}

	@Override
	public ResponseEntity<String> deleteDepartment(Long deptId) {
		Optional<Department> role = deptRepository.findById(deptId);
		if(role.isEmpty()) {
			throw new RoleNotFoundException("Department not found");
		}
		deptRepository.deleteById(deptId);
		return ResponseEntity.ok("Delete department successfully !!");
	}

}
