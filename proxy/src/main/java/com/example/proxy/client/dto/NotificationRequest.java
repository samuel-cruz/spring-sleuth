package com.example.proxy.client.dto;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.hateoas.RepresentationModel;

/**
 * @author samuel-cruz
 */
@Getter
@Builder
@EqualsAndHashCode(callSuper = true)
public class NotificationRequest extends RepresentationModel<NotificationRequest> {
    private final String id;
    private final String to;
    private final String subject;
    private final String message;
}
