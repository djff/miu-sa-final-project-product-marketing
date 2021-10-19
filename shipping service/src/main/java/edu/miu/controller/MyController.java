package edu.miu.controller;

import edu.miu.model.ResponseFormat;
    //import edu.miu.model.Shipment;
    //import edu.miu.service.KafkaProducer;
import edu.miu.service.KafkaProducer;
import edu.miu.service.MyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
@RestController
@RequestMapping("api/shipping")
public class MyController {
    @Autowired
    private MyService myService;
    @Autowired
    private KafkaProducer kafkaProducer;

    @GetMapping("{id}")
    public ResponseEntity<ResponseFormat> shippingStatus(@PathVariable UUID id){
        return ResponseEntity.ok(myService.processResponse(id));

    }

    @PostMapping("/produce")
    public ResponseEntity<Void> produce(@RequestBody ResponseFormat s){
        this.kafkaProducer.writeMessage(s);
        return ResponseEntity.ok().build();
    }

}
