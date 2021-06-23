package com.example.notification.service;

import com.example.notification.dto.NotificationRequest;
import com.example.notification.model.Notification;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author samuel-cruz
 */
@Service
public class NotificationService {
    private final Map<String, Notification> notifications = new HashMap<>();

    public String add(final NotificationRequest notificationRequest, final String origin) {
        final String id = UUID.randomUUID().toString().replace("-", "");

        notifications.put(id,
            Notification.builder()
                .id(id)
                .to(notificationRequest.getTo())
                .subject(notificationRequest.getSubject())
                .message(notificationRequest.getMessage())
                .origin(origin)
                .build()
        );
        return id;
    }

    public Optional<NotificationRequest> get(final String id) {
        final Notification notification = notifications.get(id);
        if (notification == null) {
            return Optional.empty();
        }

        return Optional.of(toNotificationRequest(notification));
    }

    public List<NotificationRequest> getAll() {
        final List<NotificationRequest> notificationList = new ArrayList<>();

        notifications.values()
            .stream()
            .forEach(notification -> notificationList.add(toNotificationRequest(notification)));

        return notificationList;
    }

    private NotificationRequest toNotificationRequest(final Notification notification) {
        return NotificationRequest.builder()
            .id(notification.getId())
            .to(notification.getTo())
            .subject(notification.getSubject())
            .message(notification.getMessage())
            .origin(notification.getOrigin())
            .build();
    }
}
