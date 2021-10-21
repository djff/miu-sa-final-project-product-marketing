package com.miusaatega.affiliateservice.controller;

import com.google.gson.Gson;
import com.miusaatega.affiliateservice.model.OrderSystemTransactionDto;
import com.miusaatega.affiliateservice.model.TransactionDto;
import com.miusaatega.affiliateservice.services.KafkaHandler;
import com.miusaatega.affiliateservice.services.TransactionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/affiliates/transaction")
@RefreshScope
@Slf4j
public class TransactionController {

    @Autowired
    TransactionService transactionService;

    @Autowired
    KafkaHandler kafkaHandler;

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;


    @PostMapping()
    public ResponseEntity<?> createTransaction(@RequestBody TransactionDto data) {
        transactionService.save(data);
        return ResponseEntity.ok("data saved successfully");
    }

    @GetMapping
    public ResponseEntity<List<TransactionDto>> getAll() {
//        log.info("findAll controller");
        return ResponseEntity.ok(transactionService.findAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<TransactionDto> getAccountById(@PathVariable(value = "id") String id) {
        return ResponseEntity.ok().body(transactionService.findById(id));
    }

    @PostMapping("kafkaProduce")
    public ResponseEntity<?> produceKafka(@RequestBody OrderSystemTransactionDto data) {
        kafkaHandler.writeToOrderChannel(new Gson().toJson(data));
        return ResponseEntity.ok("data saved successfully");
    }
}
