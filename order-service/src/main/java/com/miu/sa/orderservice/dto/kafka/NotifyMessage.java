package com.miu.sa.orderservice.dto.kafka;

import lombok.*;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
public class NotifyMessage {
    public String subject;
    public String message;
    public String email;
}
