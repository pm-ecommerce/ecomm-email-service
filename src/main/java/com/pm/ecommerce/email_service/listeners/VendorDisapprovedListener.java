package com.pm.ecommerce.email_service.listeners;

import com.pm.ecommerce.email_service.events.VendorDisapprovedEvent;
import com.pm.ecommerce.email_service.repositories.NotificationRepository;
import com.pm.ecommerce.entities.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class VendorDisapprovedListener implements ApplicationListener<VendorDisapprovedEvent> {
    @Autowired
    QueueNotification queueNotification;

    @Autowired
    NotificationRepository repository;

    @Autowired
    TemplateParser parser;

    @Override
    public void onApplicationEvent(VendorDisapprovedEvent event) {
        Notification notification = new Notification();
        notification.setReceiver(event.getVendor().getEmail());
        notification.setSubject("Your account has been disapproved.");
        String message = parser.parseTemplate("templates/vendor-disapproved", event.getVendor());
        notification.setMessage(message);
        queueNotification.queue(notification);
    }
}

