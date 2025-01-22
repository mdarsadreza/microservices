package com.employee.utils;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.stereotype.Component;

import com.employee.model.Department;

@Component
public class DepartmentConverter {

	public Department convertRequestToObj(Request request) {
		Department dept = new Department();
		dept.setDepartmentAddress(request.getAddress());
		dept.setDepartmentCode(request.getCode());
		dept.setDepartmentName(request.getName());
		return dept;
	}
	
	public Response convertObjToResonse(Department dept) {
		Response response = new Response();
		response.setAddress(dept.getDepartmentAddress());
		response.setCode(dept.getDepartmentCode());
		response.setName(dept.getDepartmentName());
		response.setId(dept.getDepartmentId());
		return response;
	}
	
	
	public static void streamOfCah() {
		Map<Object, Long> countChar = Stream.of('a','s','c','a','d','a','c').collect(Collectors.groupingBy(x->x, Collectors.counting()));
		System.out.println(countChar);
		
		Set<String> filterString = Stream.of("Arsad","Arsad","Amber","amber","Zebra","Java").filter(x -> x.toLowerCase().contains("a".toLowerCase()))
		.collect(Collectors.toSet());
		System.out.println(filterString);

	}
}
