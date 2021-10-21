package com.miu.sa.orderservice.dto.requests;

import com.miu.sa.orderservice.models.Product;
import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequest {
    private String fname;
    private String lname;
    private String email;
    private String address;
    private List<Product> products;
}
