package com.banurns.skladbanurnsrest.repository;

import com.banurns.skladbanurnsrest.model.Store;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoresRepository extends JpaRepository<Store, Long> {
}
