package com.example.proxy.service;

import com.example.proxy.client.dto.NotificationRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

/**
 * @author samuel-cruz
 */
@Service
public class ProxyService {

    private final List<String> listOfDestinationNotification;
    private final List<String> listOfSubjectNotification;
    private final List<String> listOfMessageNotification;

    public ProxyService() {
        listOfDestinationNotification = listOfDestinationNotification();
        listOfSubjectNotification = listOfSubjectNotification();
        listOfMessageNotification = listOfMessageNotification();
    }

    public NotificationRequest createNotification() {
        return NotificationRequest.builder()
            .to(getRandomItemFromList(listOfDestinationNotification))
            .subject(getRandomItemFromList(listOfSubjectNotification))
            .message(getRandomItemFromList(listOfMessageNotification))
            .build();
    }

    private String getRandomItemFromList(final List<String> list) {
        return list.get(new Random().nextInt(list.size()));
    }

    private List<String> listOfDestinationNotification() {
        final List<String> result = new ArrayList<>();

        result.add("one@email.com");
        result.add("any@email.com");
        result.add("other@email.com");
        result.add("+551133445566");
        result.add("+551133447788");
        result.add("+551133448899");
        result.add("+5541999999999");
        result.add("+5541988776655");
        result.add("+5511944332211");

        return result;
    }

    private List<String> listOfSubjectNotification() {
        final List<String> result = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            result.add("Subject - " + i);
        }

        return result;
    }

    private List<String> listOfMessageNotification() {
        final List<String> result = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            result.add("this message contains a random identifier " + UUID.randomUUID().toString());
        }

        return result;
    }
}
