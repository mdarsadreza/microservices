package com.api.gateway.dto;

public class Role {

    private Long id;

    private RoleType name;  // Role name (e.g., "ADMIN", "NORMAL", SUPER_USER)

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public com.api.gateway.dto.RoleType getName() {
        return name;
    }

    public void setName(com.api.gateway.dto.RoleType name) {
        this.name = name;
    }
}
