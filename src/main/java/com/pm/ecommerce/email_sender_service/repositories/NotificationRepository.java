package com.pm.ecommerce.email_sender_service.repositories;

import com.pm.ecommerce.entities.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {
}
