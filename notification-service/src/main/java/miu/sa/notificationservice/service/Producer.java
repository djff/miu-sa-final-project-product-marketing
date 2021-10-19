package miu.sa.notificationservice.service;

import com.google.gson.JsonObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class Producer {

    public static final String notification_topic = "notification-topic";
    public static final String payment_topic = "payment_tpc";

    private final KafkaTemplate<String, String> kafkaTemplate;

    public Producer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void publishToOrderTopic(String message) {
        log.info("Publishing to notification topic "+ message);
        this.kafkaTemplate.send(notification_topic, message);
    }

}


