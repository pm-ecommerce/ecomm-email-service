package com.pm.ecommerce.email_service.listeners;

import com.pm.ecommerce.email_service.event_data.EmailScheduledDelivery;
import com.pm.ecommerce.email_service.events.ScheduledDeliveryEvent;
import com.pm.ecommerce.email_service.repositories.NotificationRepository;
import com.pm.ecommerce.entities.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class ScheduledDeliveryEventListener implements ApplicationListener<ScheduledDeliveryEvent> {
    @Autowired
    QueueNotification queueNotification;

    @Autowired
    NotificationRepository repository;

    @Autowired
    TemplateParser parser;

    @Override
    public void onApplicationEvent(ScheduledDeliveryEvent event) {
        Notification notification = new Notification();
        notification.setReceiver(event.getDelivery().getVendor().getEmail());
        notification.setSubject("You have received a new order.");
        String message = parser.parseTemplate("templates/scheduled-delivery", new EmailScheduledDelivery(event.getDelivery()));
        notification.setMessage(message);
        queueNotification.queue(notification);
    }
}
