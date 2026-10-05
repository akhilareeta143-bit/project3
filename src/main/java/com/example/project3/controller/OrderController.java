package com.example.project3.controller;

import com.example.project3.entity.Order;
import com.example.project3.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class OrderController {

    @Autowired
    private OrderService service;

    @PostMapping("/order")
    public Order placeOrder(@RequestBody Order order) {
        return service.placeOrder(order);
    }

    @GetMapping("/orders")
    public List<Order> getOrdersByEmail(@RequestParam String email) {
        return service.getOrdersByEmail(email);
    }
}