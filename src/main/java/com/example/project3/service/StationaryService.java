package com.example.project3.service;

import com.example.project3.entity.Stationary;
import com.example.project3.repository.StationaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;



    @Service
    public class StationaryService {

        @Autowired
        private StationaryRepository repository;

        public Stationary saveStationary(Stationary stationary) {
            return repository.save(stationary);
        }

        public List<Stationary> getAllStationary() {
            return repository.findAll();
        }

        public Stationary getStationaryById(Integer id) {
            return repository.findById(id).orElse(null);
        }

        public void deleteStationary(Integer id) {
            repository.deleteById(id);
        }

        public List<Stationary> searchStationary(String keyword) {
            return repository.findByNameContainingIgnoreCase(keyword);
        }

        public List<Stationary> getAllStationarys() {
            return repository.findAll();
        }
    }

