package com.miu.sa.orderservice.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerBillingInfo {
    private String fname;
    private String lname;
    private String phone;
    private String address;
    private String email;
}
