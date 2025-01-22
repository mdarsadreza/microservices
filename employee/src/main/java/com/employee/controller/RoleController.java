package com.employee.controller;

import com.employee.service.DepartmentService;
import com.employee.service.RoleService;
import com.employee.utils.Request;
import com.employee.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/apis/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @PostMapping("/save")
    public Response save(@RequestBody Request req) {
        return roleService.save(req);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Response> findById(@PathVariable("id") Long departmentId) {
        return roleService.findById(departmentId);
    }

    @GetMapping("/findByName/{roleName}")
    public ResponseEntity<Response> findById(@PathVariable("roleName") String roleName) throws Exception {
        return roleService.findRoleByName(roleName);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id) throws Exception {
        return roleService.deleteRole(id);
    }
}
