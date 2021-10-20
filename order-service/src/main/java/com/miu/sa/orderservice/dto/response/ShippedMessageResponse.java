package com.miu.sa.orderservice.dto.response;

import lombok.*;

import java.util.UUID;

@NoArgsConstructor
public class ShippedMessageResponse {
    public static final class ShippingData{
        public UUID orderId;
        public String trackingNumber;
    }
    public ShippedMessageResponse.ShippingData data;
}
