package com.pm.ecommerce.email_service.controllers;

import com.pm.ecommerce.email_service.events.NewOrderEvent;
import com.pm.ecommerce.email_service.events.ScheduledDeliveryEvent;
import com.pm.ecommerce.email_service.listeners.NewOrderEventListener;
import com.pm.ecommerce.email_service.listeners.ScheduledDeliveryEventListener;
import com.pm.ecommerce.email_service.repositories.OrderRepository;
import com.pm.ecommerce.email_service.repositories.ScheduledDeliveryRepository;
import com.pm.ecommerce.entities.Order;
import com.pm.ecommerce.entities.ScheduledDelivery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/test")
public class TestController {
    @Autowired
    OrderRepository repository;
    @Autowired
    ScheduledDeliveryRepository deliveryRepository;

    @Autowired
    NewOrderEventListener listener;
    @Autowired
    ScheduledDeliveryEventListener deliveryEventListener;

    @GetMapping("")
    public ResponseEntity<String> readEmail() {
        try {
            Order order = repository.findById(8).orElse(null);
            if (order == null) {
                throw new Exception("Order not found.");
            }
            listener.onApplicationEvent(new NewOrderEvent(order, order));
            System.out.println("SChedules");

            ScheduledDelivery delivery = deliveryRepository.findById(6).orElse(null);
            deliveryEventListener.onApplicationEvent(new ScheduledDeliveryEvent(delivery, delivery));
            System.out.println("SChedules");
            return ResponseEntity.ok("Test");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return ResponseEntity.ok(null);
    }
}
