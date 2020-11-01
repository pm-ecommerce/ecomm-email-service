package com.pm.ecommerce.email_service.listeners;

import com.pm.ecommerce.email_service.events.OrderCompleteEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class OrderCompleteListener implements ApplicationListener<OrderCompleteEvent> {
    @Override
    public void onApplicationEvent(OrderCompleteEvent orderCompleteEvent) {

    }
}
