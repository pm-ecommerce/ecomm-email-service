package com.pm.ecommerce.email_service.listeners;

import com.pm.ecommerce.email_service.events.ScheduledDeliveryEvent;
import com.pm.ecommerce.email_service.repositories.NotificationRepository;
import com.pm.ecommerce.entities.Notification;
import com.pm.ecommerce.entities.Product;
import com.pm.ecommerce.entities.ScheduledDelivery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class ScheduledDeliveryEventListener implements ApplicationListener<ScheduledDeliveryEvent> {
    @Autowired
    KafkaTemplate template;

    @Autowired
    NotificationRepository repository;
    @Override
    public void onApplicationEvent(ScheduledDeliveryEvent deliveryEvent) {
        ScheduledDelivery delivery = deliveryEvent.getDelivery();
        System.out.println(delivery);
        // create a notification here
        Notification notification = new Notification();
        notification.setSender("sa.giri@miu.edu");
        notification.setReceiver(delivery.getVendor().getEmail());
        notification.setSubject("Your product disapproved");
        String contentsOfFile = "";
        // read the file contents
        // populate the file with real data
        // set the populated file as notification message
        notification.setMessage(contentsOfFile);
        repository.save(notification);
        template.send("NotificationTopic", notification.getId());
    }
}
