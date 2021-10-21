package com.miusaatega.affiliateservice.model.entity;

import com.miusaatega.affiliateservice.model.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document
public class Transaction implements Serializable {
    @Id
    private String id;
    private String transRef;
    private Double amount;
    private List<Product> products;
    private LocalDateTime transDate;
    private String paymentStatus;
}
