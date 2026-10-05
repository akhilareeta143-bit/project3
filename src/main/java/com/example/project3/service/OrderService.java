package com.example.project3.service;

import com.example.project3.entity.Order;
import com.example.project3.entity.Stationary;
import com.example.project3.repository.OrderRepository;
import com.example.project3.repository.StationaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository repository;

    @Autowired
    private StationaryRepository stationaryRepository;

    @Autowired
    private EmailService emailService;

    public Order placeOrder(Order order) {

        Stationary product =
                stationaryRepository.findById(order.getProductId()).orElse(null);

        if (product == null) {
            throw new RuntimeException("Product not found");
        }

        if (product.getStock() < order.getQuantity()) {
            throw new RuntimeException("Not enough stock");
        }

        int newStock = product.getStock() - order.getQuantity();

        product.setStock(newStock);

        stationaryRepository.save(product);

        Order savedOrder = repository.save(order);

        // Send confirmation email
        emailService.sendOrderConfirmation(
                order.getEmail(),
                order.getCustomerName(),
                product.getName(),
                order.getQuantity(),
                order.getTotalAmount()
        );

        return savedOrder;
    }

    public List<Order> getOrdersByEmail(String email) {
        return repository.findByEmail(email);
    }
}