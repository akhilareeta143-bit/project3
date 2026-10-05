package com.example.project3.entity;


import jakarta.persistence.*;
import lombok.Data;

    @Entity
    @Data
    public class Stationary {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer id;

        private String name;
        private String brand;
        private String colour;
        private Double price;
        private Integer stock;

        private String imageName;
        private String imageType;

        @Lob
        @Column(columnDefinition = "LONGBLOB")
        private byte[] imageData;

}

