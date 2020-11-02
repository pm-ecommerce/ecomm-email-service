package com.pm.ecommerce.email_service.listeners;

import com.pm.ecommerce.email_service.event_data.EmailProduct;
import com.pm.ecommerce.email_service.events.ProductDisapprovedEvent;
import com.pm.ecommerce.email_service.repositories.NotificationRepository;
import com.pm.ecommerce.entities.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class ProductDisapprovedListener implements ApplicationListener<ProductDisapprovedEvent> {
    @Autowired
    QueueNotification queueNotification;

    @Autowired
    NotificationRepository repository;

    @Autowired
    TemplateParser parser;

    @Override
    public void onApplicationEvent(ProductDisapprovedEvent event) {
        Notification notification = new Notification();
        notification.setReceiver(event.getProduct().getVendor().getEmail());
        notification.setSubject("You product has been disapproved.");
        String message = parser.parseTemplate("templates/product-disapproved", new EmailProduct(event.getProduct()));
        notification.setMessage(message);
        queueNotification.queue(notification);
    }
}

