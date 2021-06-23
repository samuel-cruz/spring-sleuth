package com.example.notification.controller;

import com.example.notification.dto.NotificationRequest;
import com.example.notification.service.NotificationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * @author samuel-cruz
 */
@RestController
@RequestMapping(path = "/v1/notifications")
@Slf4j
public class NotificationController {

    @Autowired
    private NotificationService notificationService;
    @Autowired
    private Tracer tracer;

    @PostMapping
    public ResponseEntity<String> createNotifications(@RequestBody final NotificationRequest notificationRequest) {
        final String origin = tracer.currentSpan().context().traceId();
        final String id = notificationService.add(notificationRequest, origin);

        log.info("Notification created - id {} - trace {}", id, origin);
        return ResponseEntity.status(HttpStatus.CREATED).body(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<NotificationRequest> getNotification(@PathVariable final String id) {
        final Optional<NotificationRequest> notification = notificationService.get(id);
        if (notification.isEmpty()) {
            log.info("Notification {} not found", id);
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(notification.get());
    }

    @GetMapping
    public ResponseEntity<List<NotificationRequest>> getNotifications() {
        final List<NotificationRequest> notifications = notificationService.getAll();
        if (notifications.isEmpty()) {
            log.info("Notifications not found");
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(notifications);
    }
}
