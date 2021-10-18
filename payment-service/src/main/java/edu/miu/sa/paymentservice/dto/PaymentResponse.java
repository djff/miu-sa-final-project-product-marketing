package edu.miu.sa.paymentservice.dto;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class PaymentResponse {
    private String orderNumber;
    private String PaymentReference;

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getPaymentReference() {
        return PaymentReference;
    }

    public void setPaymentReference(String paymentReference) {
        PaymentReference = paymentReference;
    }
}


