package com.pm.ecommerce.email_service.listeners;

import com.pm.ecommerce.email_service.events.OrderShippedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class OrderShippedListener implements ApplicationListener<OrderShippedEvent> {
    @Override
    public void onApplicationEvent(OrderShippedEvent orderShippedEvent) {

    }
}
