package com.miusaatega.affiliateservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderSystemTransactionDto {
    private String orderId;
    private Double amount;
    private List<Product> productList;
    private String dateCreated;
    private String paymentStatus;
}
