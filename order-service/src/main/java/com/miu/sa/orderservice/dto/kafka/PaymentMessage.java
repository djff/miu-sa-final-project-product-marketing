package com.miu.sa.orderservice.dto.kafka;

import lombok.*;

import java.util.Map;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
public class PaymentMessage {
    public static final class PaymentResponse{
        public UUID orderNumber;
        public String paymentReference;
    }
    public boolean successful;
    public String responseCode;
    public String responseDescription;
    public PaymentMessage.PaymentResponse paymentResponse;
}
