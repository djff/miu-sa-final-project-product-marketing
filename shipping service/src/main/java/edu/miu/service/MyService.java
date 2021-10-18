package edu.miu.service;

import edu.miu.model.ResponseFormat;
import edu.miu.model.Shipment;
import edu.miu.repository.ShipmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.UUID;

@Service
public class MyService {

    @Autowired
    private ShipmentRepository shipmentRepository;
    public String getTrackingNumber() {
        return "DHL"+ MyService.gen();
    }


    public MyService(){
    }

    public static int gen(){
        Random r = new Random(System.currentTimeMillis());
        return 10000 + r.nextInt(20000);
    }

    public Shipment processOrder(UUID Id){
        Shipment shipment = new Shipment();
        shipment.setOrderId(Id);
        shipment.setTrackingNumber(getTrackingNumber());
        shipment.setStatus("shipped");
        return shipmentRepository.save(shipment);
    }
    public ResponseFormat processResponse(UUID Id){
        ResponseFormat responseFormat = new ResponseFormat();
        responseFormat.setMessage("shipment successful!");
        responseFormat.setStatus(Boolean.TRUE);
        responseFormat.setData(processOrder(Id));
        return responseFormat;
    }

}