package edu.miu.sa.paymentservice.config;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConfiguration {
    @Value("${spring.kafka.producer.bootstrap-servers}")
    private String bootstrapServer;

    @Bean
    public ProducerFactory<String, Object> producerFactory(){
        Map<String, Object> kafkaConfig = new HashMap<>();

        //get value from application config file
        kafkaConfig.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServer);
        kafkaConfig.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        kafkaConfig.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        return new DefaultKafkaProducerFactory<>(kafkaConfig);
    }

    @Bean
    public KafkaTemplate<String, Object> kafkaTemplate(){
        return new KafkaTemplate<>(producerFactory());
    }
}
