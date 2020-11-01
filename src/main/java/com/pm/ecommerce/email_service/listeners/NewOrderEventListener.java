package com.pm.ecommerce.email_service.listeners;

import com.pm.ecommerce.email_service.events.NewOrderEvent;
import com.pm.ecommerce.email_service.repositories.NotificationRepository;
import com.pm.ecommerce.entities.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class NewOrderEventListener implements ApplicationListener<NewOrderEvent> {
    @Autowired
    KafkaTemplate template;

    @Autowired
    NotificationRepository repository;

    @Override
    public void onApplicationEvent(NewOrderEvent orderEvent) {
        // create a notification here
        Notification notification = new Notification();
        notification.setSender("sa.giri@miu.edu");
        notification.setReceiver(orderEvent.getOrder().getUser().getEmail());
        notification.setSubject("We have received your order");
        String contentsOfFile = "";
        // read the file contents
        // populate the file with real data
        // set the populated file as notification message
        notification.setMessage(contentsOfFile);
        repository.save(notification);
        template.send("NotificationTopic", notification.getId());
    }
}
