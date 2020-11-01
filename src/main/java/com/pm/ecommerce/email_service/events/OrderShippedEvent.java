package com.pm.ecommerce.email_service.events;

import com.pm.ecommerce.entities.Order;
import org.springframework.context.ApplicationEvent;

public class OrderShippedEvent extends ApplicationEvent {
    private final Order order;
    public OrderShippedEvent(Object source, Order order) {
        super(source);
        this.order = order;
    }
    public Order getOrder() {
        return order;
    }
}
