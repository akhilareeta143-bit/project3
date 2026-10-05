package com.example.project3.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

    @Service
    public class EmailService {

        @Autowired
        private JavaMailSender mailSender;

        public void sendOrderConfirmation(
                String toEmail,
                String customerName,
                String productName,
                int quantity,
                double totalAmount) {

            SimpleMailMessage message = new SimpleMailMessage();

            message.setTo(toEmail);
            message.setSubject("Order Confirmation - Stationary Management");

            message.setText(
                    "Hello " + customerName + ",\n\n" +
                            "Your order has been placed successfully!\n\n" +
                            "Product: " + productName + "\n" +
                            "Quantity: " + quantity + "\n" +
                            "Total Amount: ₹" + totalAmount + "\n\n" +
                            "Thank you for your order!\n\n" +
                            "Stationary Management"
            );

            mailSender.send(message);
        }
    }

