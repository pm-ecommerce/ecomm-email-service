package com.pm.ecommerce.email_service.listeners;

import com.pm.ecommerce.email_service.events.NewOrderEvent;
import com.pm.ecommerce.email_service.repositories.NotificationRepository;
import com.pm.ecommerce.entities.Notification;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

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
        String html = "";
        try {
            File file = new File("../../main/resources/templates/order-received.html");
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String data = scanner.nextLine();
                html += data;
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        Document doc = Jsoup.parse(html);
        // read the file contents
        // populate the file with real data
        // set the populated file as notification message
        notification.setMessage(doc.body().toString());
        repository.save(notification);
        template.send("NotificationTopic", notification.getId());
    }
}
