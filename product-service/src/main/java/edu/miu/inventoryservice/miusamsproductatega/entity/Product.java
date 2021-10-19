package edu.miu.inventoryservice.miusamsproductatega.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import  java.io.Serializable;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document
public class Product implements Serializable {
    private static final long serialVersionUID = -7817224776021728682L;

    private UUID productId;
    private String name;
    private String vendor;
    private Long quantity;
    private Double amount;
    private String category;
     }


