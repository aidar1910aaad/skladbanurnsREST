package com.banurns.skladbanurnsrest.service;

import com.banurns.skladbanurnsrest.model.Role;
import com.banurns.skladbanurnsrest.model.User;

import java.util.List;

public interface UserService {
    User register(User user, Long roleId);

    List<User> getAll();

    User findByUsername(String username);

    User findById(Long id);

    void deleteUser(Long id);

    List<Role> getRolesById(Long id);
}
