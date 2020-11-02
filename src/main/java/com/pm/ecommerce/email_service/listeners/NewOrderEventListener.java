package com.pm.ecommerce.email_service.listeners;

import com.pm.ecommerce.email_service.event_data.UserOrderReceived;
import com.pm.ecommerce.email_service.events.NewOrderEvent;
import com.pm.ecommerce.email_service.repositories.NotificationRepository;
import com.pm.ecommerce.entities.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class NewOrderEventListener implements ApplicationListener<NewOrderEvent> {
    @Autowired
    QueueNotification queueNotification;

    @Autowired
    NotificationRepository repository;

    @Autowired
    TemplateParser parser;

    @Override
    public void onApplicationEvent(NewOrderEvent orderEvent) {
        Notification notification = new Notification();
        notification.setReceiver(orderEvent.getOrder().getUser().getEmail());
        notification.setSubject("We have received your order");
        String message = parser.parseTemplate("templates/order-received", new UserOrderReceived(orderEvent.getOrder()));
        notification.setMessage(message);
        queueNotification.queue(notification);
    }
}
