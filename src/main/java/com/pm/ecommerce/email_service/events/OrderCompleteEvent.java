package com.pm.ecommerce.email_service.events;

import com.pm.ecommerce.entities.Order;
import org.springframework.context.ApplicationEvent;

public class OrderCompleteEvent extends ApplicationEvent {
    private final Order order;
    public OrderCompleteEvent(Object source, Order order) {
        super(source);
        this.order = order;
    }

    public Order getOrder() {
        return order;
    }
}
