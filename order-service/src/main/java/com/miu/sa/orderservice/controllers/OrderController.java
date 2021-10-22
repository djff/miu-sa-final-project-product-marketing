package com.miu.sa.orderservice.controllers;

import com.google.gson.Gson;
import com.miu.sa.orderservice.dto.response.GenericResponse;
import com.miu.sa.orderservice.models.Order;
import com.miu.sa.orderservice.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplateTest;

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

    @PostMapping("/test")
    public void pOrder(@RequestBody Object order){
        kafkaTemplateTest.send("payment_response_topic", new Gson().toJson(order));
    }

}
