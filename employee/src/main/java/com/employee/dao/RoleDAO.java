package com.employee.dao;

import com.employee.model.Role;
import com.employee.utils.Enums.RoleType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleDAO extends CrudRepository<Role, Long> {
    Optional<Role> findByName(RoleType roleName);
}