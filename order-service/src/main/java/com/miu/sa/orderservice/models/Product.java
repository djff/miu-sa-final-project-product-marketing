package com.miu.sa.orderservice.models;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    private Long productId;
    private int quantity;
    private double price;
    private double markUp;
    private String affiliateId;
}
