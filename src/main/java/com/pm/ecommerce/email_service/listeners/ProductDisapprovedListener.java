package com.pm.ecommerce.email_service.listeners;

import com.pm.ecommerce.email_service.events.ProductDisapprovedEvent;
import com.pm.ecommerce.email_service.repositories.NotificationRepository;
import com.pm.ecommerce.entities.Notification;
import com.pm.ecommerce.entities.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class ProductDisapprovedListener implements ApplicationListener<ProductDisapprovedEvent> {
    @Autowired
    KafkaTemplate template;

    @Autowired
    NotificationRepository repository;
    @Override
    public void onApplicationEvent(ProductDisapprovedEvent productDisapprovedEvent) {
        Product product = productDisapprovedEvent.getProduct();
        // create a notification here
        Notification notification = new Notification();
        notification.setSender("sa.giri@miu.edu");
        notification.setReceiver(product.getVendor().getEmail());
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
