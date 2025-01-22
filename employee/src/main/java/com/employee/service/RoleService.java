package com.employee.service;

import com.employee.utils.Request;
import com.employee.utils.Response;
import org.springframework.http.ResponseEntity;

public interface RoleService {

    public Response save(Request request);
    public ResponseEntity<Response> findById(Long roleId);
    public ResponseEntity<Response> findRoleByName(String roleName) throws Exception ;
    ResponseEntity<String> deleteRole(Long id);
}