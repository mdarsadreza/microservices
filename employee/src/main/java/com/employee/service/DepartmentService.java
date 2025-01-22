package com.employee.service;

import org.springframework.http.ResponseEntity;

import com.employee.utils.Request;
import com.employee.utils.Response;

public interface DepartmentService {
	
	public Response save(Request dept);
	public ResponseEntity<Response> findById(Long deptId);
	public ResponseEntity<Response> findDepartmentCode(String deptName) throws Exception ;

	ResponseEntity<Response> editDepartment(Long deptId, Request request);

	ResponseEntity<String> deleteDepartment(Long deptId);
}
