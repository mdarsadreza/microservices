package com.employee.utils;

import com.employee.config.PasswordEncryptionService;
import com.employee.dao.DepartmentDAO;
import com.employee.dao.RoleDAO;
import com.employee.exception.DepartmentNotFoundException;
import com.employee.exception.RoleNotFoundException;
import com.employee.model.Department;
import com.employee.model.Employee;
import com.employee.model.Role;
import com.employee.utils.Enums.RoleType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class EmployeeConverter {

    @Autowired
    private PasswordEncryptionService passwordEncryptionService;
    @Autowired
    private DepartmentConverter departmentConverter;
    @Autowired
    private RoleDAO roleDAO;
    @Autowired
    private DepartmentDAO deptDAO;
    public Employee convertRequestToObj(Request request) {

        System.out.println(request);
        Department department = deptDAO.findById(request.getDeptId())
                .orElseThrow(() -> new DepartmentNotFoundException("Department not found "+request.getDeptId()));
        System.out.println(department);
        long roleId = request.getRoleId();
        System.out.println("request name "+roleId);
        Optional<Role> roleData = roleDAO.findById(roleId);
        if(roleData.isEmpty()) {
            throw new RoleNotFoundException("Role not found, please add it first."+ roleId);
        }
        Role role = roleData.get();
        Employee emp = new Employee();
        try {
            emp.setName(request.getName());
            emp.setDepartment(department);
            emp.setRole(role);
            emp.setPosition(request.getPosition());
            String combinedPassword = request.getUsername() + ":" + request.getPassword();
            emp.setPassword(passwordEncryptionService.encryptPassword(combinedPassword));
            emp.setUsername(request.getUsername());

            //Console statement
            String decryptedCombined = passwordEncryptionService.decryptPassword(emp.getPassword());
            String[] parts = decryptedCombined.split(":");
            if (parts.length == 2) {
                String decryptedPassword = parts[1];
                System.out.println("Decrypted password: " + decryptedPassword);
            } else {
                System.err.println("Decryption failed. Unexpected format: " + decryptedCombined);
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e);
            throw new RoleNotFoundException("Something went wrong !! "+ e);
        } catch (Exception e) {
            throw new RoleNotFoundException("Password worng !! "+ e);
        }
        return emp;
    }

    public Response convertObjToResonse(Employee emp) {
        Response response = new Response();
        response.setName(emp.getName());
        response.setUsername(emp.getUsername());
        response.setPosition(emp.getPosition());
        if (emp.getDepartment() != null) {
            Department dept = new Department();
            dept.setDepartmentName(emp.getDepartment().getDepartmentName());
            dept.setDepartmentId(emp.getDepartment().getDepartmentId());
            dept.setDepartmentAddress(emp.getDepartment().getDepartmentAddress());
            dept.setDepartmentCode(emp.getDepartment().getDepartmentCode());
            response.setDepartment(dept);
        }
        if (emp.getRole() != null) {
            Role role = new Role();
            role.setId(emp.getRole().getId());
            role.setName(emp.getRole().getName());
            response.setRole(role);
        }
        response.setId(emp.getId());
        System.out.println(emp);
        System.out.println(response);
        return response;
    }
}
