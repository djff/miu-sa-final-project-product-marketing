package edu.miu.service;

import com.google.gson.Gson;
import edu.miu.model.ResponseFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducer {
    private static final String TOPIC = "kafka_topic";
    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    public void writeMessage(String msg){
        this.kafkaTemplate.send(TOPIC,msg);
    }

    public void writeMessage(ResponseFormat msg){
//        System.out.println("Checking shippment status" + msg.getStatus());
        this.kafkaTemplate.send(TOPIC, new Gson().toJson(msg));
    }
}
