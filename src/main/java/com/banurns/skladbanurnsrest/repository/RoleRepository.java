package com.banurns.skladbanurnsrest.repository;

import com.banurns.skladbanurnsrest.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}
