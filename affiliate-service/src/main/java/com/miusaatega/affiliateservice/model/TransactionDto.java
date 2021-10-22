package com.miusaatega.affiliateservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionDto {
    private String id;
    private String transRef;
    private Double amount;
    private List<Product> products;
    private String transDate;
    private String paymentStatus;
}
