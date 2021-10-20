package edu.miu.sa.paymentservice.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "Transactions")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
    public Long customerReference;
    public String paymentReference;
    public String orderNumber;
    public Double amount;
    public PaymentType type;
    public PaymentStatus status;
    public LocalDateTime requestTime;
    public String responseCode;
    public LocalDateTime responseTime;
    public LocalDateTime dateCreated;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
