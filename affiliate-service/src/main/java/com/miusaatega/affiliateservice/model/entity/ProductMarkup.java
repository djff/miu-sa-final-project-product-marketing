package com.miusaatega.affiliateservice.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document
public class ProductMarkup implements Serializable {
    @Id
    private String id;
    private String productId;
    private String affiliateId;
    private String productLinkId;
    private Double amount;
    private String markupType = "FIXED";
    private boolean active;
}
