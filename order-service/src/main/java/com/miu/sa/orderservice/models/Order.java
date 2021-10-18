package com.miu.sa.orderservice.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection="orders")
public class Order {

    @Id
    private UUID orderId;
    private Boolean isPaid = false;
    private Boolean isShipped = false;
    private List<Product> productList;
    private CustomerBillingInfo customerBillingInfo;
}
