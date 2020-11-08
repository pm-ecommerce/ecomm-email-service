package com.pm.ecommerce.email_sender_service.services;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.pm.ecommerce.email_sender_service.repositories.NotificationRepository;
import com.pm.ecommerce.entities.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
public class KafkaListenerService {
    @Autowired
    NotificationRepository repository;

    @Value("${mailgun.domainName}")
    protected String domainName;

    @Value("${mailgun.apiKey}")
    protected String apiKey;

    @KafkaListener(topics = "NotificationTopic", containerGroup = "notification_group")
    public void consume(Long id) {
        try {
            Notification notification = repository.findById(id).orElse(null);
            if (notification == null) {
                throw new Exception("Notification not found id: " + id);
            }
            HttpResponse<JsonNode> request = Unirest.post("https://api.mailgun.net/v3/" + domainName + "/messages")
                    .basicAuth("api", apiKey)
                    .queryString("from", notification.getSender())
                    .queryString("to", notification.getReceiver())
                    .queryString("subject", notification.getSubject())
                    .queryString("html", notification.getMessage())
                    .asJson();
            notification.setSentDate(new Timestamp(System.currentTimeMillis()));
            notification.setDelivered(true);
            repository.save(notification);

            System.out.println(notification.getSentDate());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
