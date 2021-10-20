package com.miusaatega.affiliateservice.controller;

import com.miusaatega.affiliateservice.model.TransactionDto;
import com.miusaatega.affiliateservice.services.TransactionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/affiliates/transaction")
@RefreshScope
@Slf4j
public class TransactionController {

    @Autowired
    TransactionService transactionService;


    @PostMapping()
    public ResponseEntity<?> createproductMarkup(@RequestBody TransactionDto data) {
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
}
