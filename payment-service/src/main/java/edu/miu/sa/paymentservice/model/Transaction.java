package edu.miu.sa.paymentservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

//import javax.persistence.*;
import java.time.LocalDateTime;

@AllArgsConstructor
@Document(collection = "transactions")
public class Transaction {
    //private Long id;
    public Long customerReference;
    public String paymentReference;
    public String orderNumber;

    public Transaction() {
    }

    public Double amount;
    public PaymentType type;
    public PaymentStatus status;
    public LocalDateTime requestTime;
    public String responseCode;
    public LocalDateTime responseTime;
    public LocalDateTime dateCreated;

//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }

}
