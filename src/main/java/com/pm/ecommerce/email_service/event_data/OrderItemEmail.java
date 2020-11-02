package com.pm.ecommerce.email_service.event_data;

import com.pm.ecommerce.entities.OrderItem;

public class OrderItemEmail {
    String name;
    int quantity;
    double rate;
    double price;

    public OrderItemEmail(OrderItem item) {
        name = item.getProduct().getName();
        quantity = item.getQuantity();
        rate = item.getRate();
        price = rate * quantity;
    }
}
