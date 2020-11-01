package com.pm.ecommerce.email_service.listeners;

import com.pm.ecommerce.email_service.events.VendorDisapprovedEvent;
import com.pm.ecommerce.email_service.repositories.NotificationRepository;
import com.pm.ecommerce.entities.Notification;
import com.pm.ecommerce.entities.Vendor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class VendorDisapprovedListener implements ApplicationListener<VendorDisapprovedEvent> {
    @Autowired
    KafkaTemplate template;

    @Autowired
    NotificationRepository repository;
    @Override
    public void onApplicationEvent(VendorDisapprovedEvent vendorDisapprovedEvent) {
        Vendor vendor = vendorDisapprovedEvent.getVendor();
        // create a notification here
        Notification notification = new Notification();
        notification.setSender("sa.giri@miu.edu");
        notification.setReceiver(vendor.getEmail());
        notification.setSubject("Your account disapproved");
        String contentsOfFile = "";
        // read the file contents
        // populate the file with real data
        // set the populated file as notification message
        notification.setMessage(contentsOfFile);
        repository.save(notification);
        template.send("NotificationTopic", notification.getId());
    }
}
