package com.miu.sa.orderservice.dto.kafka;

import com.miu.sa.orderservice.models.Product;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
public class ProductMessage {
    public boolean isDeductable;
    public List<Product> productList;
}
