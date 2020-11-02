package com.pm.ecommerce.email_service.listeners;

import com.pm.ecommerce.email_service.event_data.EmailScheduledDelivery;
import com.pm.ecommerce.email_service.events.OrderCancelledEvent;
import com.pm.ecommerce.email_service.repositories.NotificationRepository;
import com.pm.ecommerce.entities.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class OrderCancelledListener implements ApplicationListener<OrderCancelledEvent> {
    @Autowired
    QueueNotification queueNotification;

    @Autowired
    NotificationRepository repository;

    @Autowired
    TemplateParser parser;

    @Override
    public void onApplicationEvent(OrderCancelledEvent event) {
        Notification notification = new Notification();
        notification.setReceiver(event.getDelivery().getUser().getEmail());
        notification.setSubject("You order has been cancelled.");
        String message = parser.parseTemplate("templates/order-cancelled", new EmailScheduledDelivery(event.getDelivery()));
        notification.setMessage(message);
        queueNotification.queue(notification);
    }
}
