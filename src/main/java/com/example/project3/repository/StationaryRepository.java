package com.example.project3.repository;

import com.example.project3.entity.Stationary;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StationaryRepository extends JpaRepository<Stationary, Integer> {
    List<Stationary> findByNameContainingIgnoreCase(String keyword);

}
