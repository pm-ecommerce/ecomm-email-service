package com.pm.ecommerce.email_service.events;

import com.pm.ecommerce.entities.ScheduledDelivery;
import org.springframework.context.ApplicationEvent;

public class OrderShippedEvent extends ApplicationEvent {
    private final ScheduledDelivery delivery;

    public OrderShippedEvent(Object source, ScheduledDelivery delivery) {
        super(source);
        this.delivery = delivery;
    }

    public ScheduledDelivery getDelivery() {
        return delivery;
    }
}
