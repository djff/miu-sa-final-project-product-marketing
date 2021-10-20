package com.miusaatega.affiliateservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductMarkupDto {
    private String id;
    private String productId;
    private String affiliateId;
    private String productLinkId;
    private Double amount;
    private String markupType;
    private boolean active;
}
