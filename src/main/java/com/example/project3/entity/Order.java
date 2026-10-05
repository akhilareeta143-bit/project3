package com.example.project3.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "orders")
@Data
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String customerName;
    private String phone;
    private String address;
    private Integer productId;
    private Integer quantity;
    private String email;
    private Double totalAmount;
    private String paymentMethod;
}

