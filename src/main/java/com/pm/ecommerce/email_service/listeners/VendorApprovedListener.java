package com.pm.ecommerce.email_service.listeners;

import com.pm.ecommerce.email_service.events.VendorApprovedEvent;
import com.pm.ecommerce.email_service.repositories.NotificationRepository;
import com.pm.ecommerce.entities.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class VendorApprovedListener implements ApplicationListener<VendorApprovedEvent> {
    @Autowired
    QueueNotification queueNotification;

    @Autowired
    NotificationRepository repository;

    @Autowired
    TemplateParser parser;

    @Override
    public void onApplicationEvent(VendorApprovedEvent event) {
        Notification notification = new Notification();
        notification.setReceiver(event.getVendor().getEmail());
        notification.setSubject("Your account has been approved.");
        String message = parser.parseTemplate("templates/vendor-approved", event.getVendor());
        notification.setMessage(message);
        queueNotification.queue(notification);
    }
}


