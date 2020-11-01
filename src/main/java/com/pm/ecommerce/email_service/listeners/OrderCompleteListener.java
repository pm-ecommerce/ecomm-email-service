package com.pm.ecommerce.email_service.listeners;

import com.pm.ecommerce.email_service.events.OrderCompleteEvent;
import com.pm.ecommerce.email_service.repositories.NotificationRepository;
import com.pm.ecommerce.entities.Notification;
import com.pm.ecommerce.entities.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class OrderCompleteListener implements ApplicationListener<OrderCompleteEvent> {
    @Autowired
    KafkaTemplate template;

    @Autowired
    NotificationRepository repository;

    @Override
    public void onApplicationEvent(OrderCompleteEvent orderCompleteEvent) {
        Order order = orderCompleteEvent.getOrder();
        // create a notification here
        Notification notification = new Notification();
        notification.setSender("sa.giri@miu.edu");
        notification.setReceiver(order.getUser().getEmail());
        notification.setSubject("Your order completed");
        String contentsOfFile = "";
        // read the file contents
        // populate the file with real data
        // set the populated file as notification message
        notification.setMessage(contentsOfFile);
        repository.save(notification);
        template.send("NotificationTopic", notification.getId());
    }
}
