package com.miu.sa.orderservice.repositories;

import com.miu.sa.orderservice.models.Order;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface OrderRepository extends MongoRepository<Order, UUID> {
}
