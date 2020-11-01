package com.pm.ecommerce.email_service.listeners;

import com.pm.ecommerce.email_service.events.ScheduledDeliveryEvent;
import com.pm.ecommerce.entities.ScheduledDelivery;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class ScheduledDeliveryEventListener implements ApplicationListener<ScheduledDeliveryEvent> {
    @Override
    public void onApplicationEvent(ScheduledDeliveryEvent deliveryEvent) {
        ScheduledDelivery delivery = deliveryEvent.getDelivery();
        System.out.println(delivery);
    }
}
