package com.miu.sa.orderservice.controllers;

import com.miu.sa.orderservice.dto.response.GenericResponse;
import com.miu.sa.orderservice.models.Order;
import com.miu.sa.orderservice.services.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    final private OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public ResponseEntity<GenericResponse> getAllOrders(){
        return ResponseEntity.ok(orderService.getAllOrders());
    }

    @PostMapping
    public ResponseEntity<GenericResponse> createOrder(@RequestBody Order order){
        return ResponseEntity.status(201).body(orderService.createNewOrder(order));
    }
}
