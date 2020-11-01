package com.pm.ecommerce.email_service.listeners;

import com.pm.ecommerce.email_service.events.NewOrderEvent;
import com.pm.ecommerce.entities.Notification;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class NewOrderEventListener implements ApplicationListener<NewOrderEvent> {
    @Override
    public void onApplicationEvent(NewOrderEvent newOrderEvent) {
        // create a notification here
        System.out.println(newOrderEvent.getOrder().getTax());
        Notification notification = new Notification();
        notification.setSender("sa.giri@miu.edu");
        notification.setReceiver("ioesandeep@gmail.com");
        notification.setSubject("We have received your order");
        // read the file contents
        // populate the file with real data
        // set the populated file as notification message
//        notification.setMessage(contentsOfFile);
//
//        repository.save(notification);
//
//        template.send("NotificationTopic", notification.getId());
    }
}
