package com.banurns.skladbanurnsrest.repository;

import com.banurns.skladbanurnsrest.model.Miscellaneous;
import com.banurns.skladbanurnsrest.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MiscellaneousRepository extends JpaRepository<Miscellaneous , Long> {
    List<Miscellaneous> findAllByStatus(Status status);
}
