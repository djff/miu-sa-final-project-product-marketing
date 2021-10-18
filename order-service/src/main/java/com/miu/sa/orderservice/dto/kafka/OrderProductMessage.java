package com.miu.sa.orderservice.dto.kafka;

import com.miu.sa.orderservice.models.Product;

import java.util.List;
import java.util.UUID;

public class OrderProductMessage {
    public UUID orderId;
    public List<Product> productList;
}
