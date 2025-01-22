package com.employee.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.employee.service.DepartmentService;
import com.employee.utils.Request;
import com.employee.utils.Response;

@RestController
@RequestMapping("/apis")
public class DepartmentController {

	@Autowired
	private DepartmentService empService;
	
	@PostMapping("/save")
	public Response save(@RequestBody Request req) {
		Response res= empService.save(req);
		return res;
	}
	
	@GetMapping("/find/{id}")
	public ResponseEntity<Response> findById(@PathVariable("id") Long departmentId) {
		ResponseEntity<Response> response = empService.findById(departmentId);
		return response;
	}
	
	@GetMapping("/findByName/{deptName}")
	public ResponseEntity<Response> findById(@PathVariable("deptName") String deptName) throws Exception {
		return empService.findDepartmentCode(deptName);
	}

	@PutMapping("/edit/{deptId}")
	public ResponseEntity<Response> edit(@PathVariable("deptId") Long deptId, @RequestBody Request request) throws Exception {
		return empService.editDepartment(deptId, request);
	}

	@DeleteMapping("/delete/{deptId}")
	public ResponseEntity<String> delete(@PathVariable("deptId") Long deptId) throws Exception {
		return empService.deleteDepartment(deptId);
	}
}
