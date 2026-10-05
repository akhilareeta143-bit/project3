package com.example.project3.controller;

import com.example.project3.entity.Stationary;
import com.example.project3.service.StationaryService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

    @RestController
    @RequestMapping("/api")
    @CrossOrigin(origins = "http://localhost:3000")
    public class StationaryController {

        @Autowired
        private StationaryService service;

        @PostMapping("/stationary")
        public Stationary addFruit(
                @RequestPart("stationary") String stationaryJson,
                @RequestPart("image") MultipartFile image) throws IOException {

            ObjectMapper mapper = new ObjectMapper();
            Stationary stationary = mapper.readValue(stationaryJson,Stationary.class);

            stationary.setImageName(image.getOriginalFilename());
            stationary.setImageType(image.getContentType());
            stationary.setImageData(image.getBytes());

            return service.saveStationary(stationary);
        }

        @GetMapping("/stationarys")
        public List<Stationary> getStationary() {
            return service.getAllStationarys();
        }

        @GetMapping("/stationary/{id}")
        public Stationary getStationaryById(@PathVariable Integer id) {
            return service.getStationaryById(id);
        }

        @GetMapping("/stationary/{id}/image")
        public ResponseEntity<byte[]> getImage(@PathVariable Integer id) {
            Stationary stationary = service.getStationaryById(id);
            return ResponseEntity.ok()
                    .contentType(MediaType.valueOf(stationary.getImageType()))
                    .body(stationary.getImageData());
        }

        @PutMapping(
                value = "/stationary/{id}",
                consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
                produces = MediaType.APPLICATION_JSON_VALUE
        )
        public ResponseEntity<Stationary> updateStationary(
                @PathVariable Integer id,
                @RequestPart("stationary") String stationaryJson,
                @RequestPart(value = "image", required = false) MultipartFile image) throws IOException {

            Stationary existing = service.getStationaryById(id);

            if (existing == null) {
                return ResponseEntity.notFound().build();
            }

            ObjectMapper mapper = new ObjectMapper();
            Stationary updated = mapper.readValue(stationaryJson, Stationary.class);

            existing.setName(updated.getName());
            existing.setBrand(updated.getBrand());
            existing.setColour(updated.getColour());
            existing.setPrice(updated.getPrice());
            existing.setStock(updated.getStock());

            if (image != null && !image.isEmpty()) {
                existing.setImageName(image.getOriginalFilename());
                existing.setImageType(image.getContentType());
                existing.setImageData(image.getBytes());
            }

            return ResponseEntity.ok(service.saveStationary(existing));
        }
        @DeleteMapping("/stationary/{id}")
        public String deleteStationary(@PathVariable Integer id) {
            service.deleteStationary(id);
            return "Stationary Deleted";
        }

        @GetMapping("/search")
        public List<Stationary> searchStationary(@RequestParam String keyword) {
            return service.searchStationary(keyword);
        }
}
