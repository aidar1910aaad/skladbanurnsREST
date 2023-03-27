package com.banurns.skladbanurnsrest.repository;

import com.banurns.skladbanurnsrest.model.Flavor;
import com.banurns.skladbanurnsrest.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FlavorsRepository extends JpaRepository<Flavor , Long> {
    List<Flavor> findAllByStatus(Status status);
}
