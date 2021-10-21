package com.miu.sa.orderservice.models;

import lombok.*;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    private UUID productId;
    private int quantity;
    private double price;
    private double markUp;
    private String affiliateId;
}
