package edu.miu.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.UUID;

@Document
public class Shipment {
    @Id
    @Field("orderID")
    private UUID orderId;
    private String trackingNumber;
    private String status;

    public Shipment() {
    }

    public void setOrderId(UUID orderId) {
        this.orderId = orderId;
    }

    public void setTrackingNumber(String trackingNumber) {
        this.trackingNumber = trackingNumber;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public UUID getOrderId() {
        return orderId;
    }

    public String getTrackingNumber() {
        return trackingNumber;
    }

    public String getStatus() {
        return status;
    }
}
