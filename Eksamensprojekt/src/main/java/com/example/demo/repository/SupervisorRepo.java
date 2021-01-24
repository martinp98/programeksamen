package com.example.demo.repository;

import com.example.demo.model.Supervisor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SupervisorRepo extends JpaRepository<Supervisor, Integer> {
}
