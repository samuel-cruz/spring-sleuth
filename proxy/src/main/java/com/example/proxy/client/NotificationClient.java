package com.example.proxy.client;

import com.example.proxy.client.dto.NotificationRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * @author samuel-cruz
 */
@FeignClient(name = "notifications", url = "localhost:8080/v1/notifications")
public interface NotificationClient {

    @RequestMapping(method = RequestMethod.POST)
    ResponseEntity<String> sendNotifications(NotificationRequest notificationRequest);

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    ResponseEntity<NotificationRequest> getNotification(@PathVariable("id") String id);

    @RequestMapping(method = RequestMethod.GET)
    ResponseEntity<List<NotificationRequest>> getNotifications();

}
