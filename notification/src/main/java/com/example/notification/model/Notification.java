package com.example.notification.model;

import lombok.Builder;
import lombok.Getter;

/**
 * @author samuel-cruz
 */
@Getter
@Builder
public class Notification {
    private final String id;
    private final String to;
    private final String subject;
    private final String message;
}
