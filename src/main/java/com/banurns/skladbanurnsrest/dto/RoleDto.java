package com.banurns.skladbanurnsrest.dto;

import com.banurns.skladbanurnsrest.model.Role;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class RoleDto {
    private String role;

    public Role toRole() {
        Role r = new Role();
        r.setName(this.role);
        return r;
    }

    public static RoleDto fromRole(Role role){
        RoleDto r = new RoleDto();
        r.setRole(role.getName());
        return r;
    }
}
