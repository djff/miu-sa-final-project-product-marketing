package com.miusaatega.affiliateservice.services;

import com.google.gson.Gson;
import com.miusaatega.affiliateservice.model.OrderSystemTransactionDto;
import com.miusaatega.affiliateservice.model.Product;
import com.miusaatega.affiliateservice.model.TransactionDto;
import com.miusaatega.affiliateservice.model.adapter.TransactionAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class KafkaHandler {

    private static final String ORDER_TOPIC = "AFFILIATE_COMPLETE_ORDER";

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;


    @KafkaListener(topics= ORDER_TOPIC, groupId = "group_id")
    public void getMessage(String message){
        System.out.println(ORDER_TOPIC + ": " + message);
        Gson gson = new Gson();
        OrderSystemTransactionDto response = gson.fromJson(message, OrderSystemTransactionDto.class);
        TransactionDto transactionDto = TransactionAdapter.getTrasactionDtoFromOrder(response);
        transactionService.save(transactionDto);
    }

    public void writeToOrderChannel(String message){
        System.out.println(ORDER_TOPIC + ": " + message);
        this.kafkaTemplate.send(ORDER_TOPIC, message);
    }
}
