package com.ootd.fitme.domain.notiication.repository;

import com.ootd.fitme.domain.notiication.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
}
