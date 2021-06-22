package com.example.proxy.controller;

import com.example.proxy.client.NotificationClient;
import com.example.proxy.client.dto.NotificationRequest;
import com.example.proxy.service.ProxyService;
import feign.FeignException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/**
 * @author samuel-cruz
 */
@RestController
@RequestMapping("/v1")
// @Slf4j
public class ProxyController {
    @Autowired
    private NotificationClient notificationClient;
    @Autowired
    private ProxyService proxyService;
    private static final Logger logger = LoggerFactory.getLogger(ProxyController.class);

    @PostMapping("/do/notifications")
    public ResponseEntity<?> createNotification() {
        logger.info("Calling the service to create a new notification...");
        final ResponseEntity<String> response = notificationClient.sendNotifications(proxyService.createNotification());

        if (response.getStatusCode().is2xxSuccessful()) {
            final RepresentationModel<NotificationRequest> notificationLink = new RepresentationModel<NotificationRequest>();
            notificationLink.add(linkTo(methodOn(getClass()).getNotification(response.getBody())).withSelfRel());

            return ResponseEntity.status(HttpStatus.CREATED).body(notificationLink);
        }

        logger.info("A new notification has been created by the service!");
        return response;
    }

    @GetMapping("/done/notifications/{id}")
    public ResponseEntity<NotificationRequest> getNotification(@PathVariable final String id) {
        logger.info("Calling the service to get a specific notification...");
        final ResponseEntity<NotificationRequest> response;
        try {
            response = notificationClient.getNotification(id);

            if (response.getStatusCodeValue() == 200) {
                response.getBody().add(linkTo(methodOn(getClass()).getNotifications()).withRel("List of notifications"));
                logger.info("A notification was found!");
            } else {
                logger.info("Not found notification with id {}!", id);
            }
        } catch (final FeignException.FeignClientException e) {
            logger.info("Not found notification with id {} - Error: {}!", id, e.getCause());
            return ResponseEntity.status(e.status()).build();
        }
        return response;
    }

    @GetMapping("/done/notifications")
    public ResponseEntity<List<NotificationRequest>> getNotifications() {
        logger.info("Call the service for a list of notifications...");
        ResponseEntity<List<NotificationRequest>> response = null;

        try {
            response = notificationClient.getNotifications();
            if (response.getStatusCodeValue() == 200) {
                response.getBody().forEach(notification ->
                    notification.add(linkTo(methodOn(getClass()).getNotification(notification.getId())).withSelfRel())
                );
                logger.info("Notifications found by the service!");
            } else {
                logger.info("Notifications not found by the service!");
            }

            return response;
        } catch (final FeignException.FeignClientException e) {
            logger.info("Notifications not found by the service! Error: {}", e.getCause());
            return ResponseEntity.status(e.status()).build();
        }
    }
}
