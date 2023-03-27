package com.banurns.skladbanurnsrest.dto;

import com.banurns.skladbanurnsrest.model.Status;
import com.banurns.skladbanurnsrest.model.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.Date;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class AdminUserDto {
    private Long id;
    private String username;
    private String firstName;
    private String lastName;
    private String status;

    private Date created;
    private Date updated;

    public User toUser() {
        User user = new User();
        user.setId(id);
        user.setUsername(username);
        user.setFirstname(firstName);
        user.setLastname(lastName);
        user.setStatus(Status.valueOf(status));
        return user;
    }

    public static AdminUserDto fromUser(User user) {
        AdminUserDto adminUserDto = new AdminUserDto();
        adminUserDto.setId(user.getId());
        adminUserDto.setUsername(user.getUsername());
        adminUserDto.setFirstName(user.getFirstname());
        adminUserDto.setLastName(user.getLastname());
        adminUserDto.setStatus(user.getStatus().name());
        adminUserDto.setCreated(user.getCreated());
        adminUserDto.setUpdated(user.getUpdated());
        return adminUserDto;
    }
}