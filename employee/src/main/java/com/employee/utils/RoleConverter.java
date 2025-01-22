package com.employee.utils;

import com.employee.exception.RoleNotFoundException;
import com.employee.model.Department;
import com.employee.model.Role;
import com.employee.utils.Enums.RoleType;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class RoleConverter {

	public Role convertRequestToObj(Request request) {
		Role role = new Role();
		try {
			role.setName(RoleType.valueOf(request.getName()));
		} catch (IllegalArgumentException e) {
			throw new RoleNotFoundException("Role not found "+ request.getName());
		}
		return role;
	}
	
	public Response convertObjToResonse(Role role) {
		Response response = new Response();
		response.setName(role.getName().name());
		response.setId(role.getId());
		return response;
	}
}
