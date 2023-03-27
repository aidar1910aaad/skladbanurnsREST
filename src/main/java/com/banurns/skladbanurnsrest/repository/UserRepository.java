package com.banurns.skladbanurnsrest.repository;

import com.banurns.skladbanurnsrest.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


public interface UserRepository extends JpaRepository<User , Long> {
    User findByUsername(String name);
}
