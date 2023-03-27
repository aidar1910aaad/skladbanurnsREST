package com.banurns.skladbanurnsrest.repository;

import com.banurns.skladbanurnsrest.model.Request;
import com.banurns.skladbanurnsrest.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RequestRepository extends JpaRepository<Request, Long> {
    List<Request> findAllByStatus(Status status);
    Request findTopByOrderByIdDesc();
}
