package com.miusaatega.affiliateservice.controller;

import com.miusaatega.affiliateservice.model.ProductMarkupDto;
import com.miusaatega.affiliateservice.model.entity.ProductMarkup;
import com.miusaatega.affiliateservice.services.ProductMarkupService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/affiliates/productMarkup")
@RefreshScope
@Slf4j
public class ProductMarkupController {

    @Autowired
    ProductMarkupService productMarkupService;


    @PostMapping()
    public ResponseEntity<?> createproductMarkup(@RequestBody ProductMarkupDto data) {
        productMarkupService.save(data);
        return ResponseEntity.ok("data saved successfully");
    }

    @GetMapping
    public ResponseEntity<List<ProductMarkupDto>> getAll() {
//        log.info("findAll controller");
        return ResponseEntity.ok(productMarkupService.findAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<ProductMarkupDto> getAccountById(@PathVariable(value = "id") String id) {
        return ResponseEntity.ok().body(productMarkupService.findById(id));
    }

    @PutMapping("{id}")
    public ResponseEntity<ProductMarkupDto> updateAccount(@PathVariable(value = "id") String id,
                                                    @RequestBody ProductMarkupDto prod) {
        return ResponseEntity.ok(productMarkupService.update(id, prod));

    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteAccount(@PathVariable String id) {
        productMarkupService.delete(id);
        return ResponseEntity.ok().build();
    }

}
