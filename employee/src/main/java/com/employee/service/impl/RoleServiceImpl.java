package com.employee.service.impl;

import com.employee.dao.RoleDAO;
import com.employee.exception.RoleNotFoundException;
import com.employee.model.Role;
import com.employee.service.RoleService;
import com.employee.utils.Enums.RoleType;
import com.employee.utils.Request;
import com.employee.utils.Response;
import com.employee.utils.RoleConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDAO roleDao;

    @Autowired
    private RoleConverter convert;

    @Override
    public Response save(Request request) {
        Role role= convert.convertRequestToObj(request);
        roleDao.save(role);
        Response response = convert.convertObjToResonse(role);
        return response;
    }

    @Override
    public ResponseEntity<Response> findById(Long deptId) {
        Optional<Role> role = roleDao.findById(deptId);
        if(role.isEmpty()) {
            throw new RoleNotFoundException("Role not found, please add it first.");
        }
        Response response = convert.convertObjToResonse(role.get());
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<Response> findRoleByName(String roleName) throws Exception {
        RoleType roleType;
        try {
            roleType = RoleType.valueOf(roleName.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new RoleNotFoundException("Please enter a correct role name.");
        }

        Optional<Role> role = roleDao.findByName(roleType);
        if (role.isEmpty()) {
            throw new RoleNotFoundException("Role not found for the given role name.");
        }
        Response response = convert.convertObjToResonse(role.get());
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<String> deleteRole(Long id) {
        Optional<Role> role = roleDao.findById(id);
        if(role.isEmpty()) {
            throw new RoleNotFoundException("Role not found, please add it first.");
        }
        roleDao.deleteById(id);
        return ResponseEntity.ok("Delete role successfully !!");
    }

}
