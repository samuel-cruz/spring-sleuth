package com.example.notification.dto;

import lombok.Builder;
import lombok.Data;

/**
 * @author samuel-cruz
 */
@Data
@Builder
public class NotificationRequest {
    private String id;
    private String to;
    private String subject;
    private String message;
}
