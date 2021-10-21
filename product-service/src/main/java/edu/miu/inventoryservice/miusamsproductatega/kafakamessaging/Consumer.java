package edu.miu.inventoryservice.miusamsproductatega.kafakamessaging;


import edu.miu.inventoryservice.miusamsproductatega.dto.ProductOrder;
import edu.miu.inventoryservice.miusamsproductatega.service.ProductService;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public final class Consumer {
    private static final Logger logger = LoggerFactory.getLogger(Consumer.class);

    private final ProductService service;

    public Consumer(ProductService service) {
        this.service = service;
    }

    @KafkaListener(topics = "product-topic")
    public void consume(String message) {
        JSONObject json = new JSONObject(message);
        boolean isDeductable = json.getBoolean("isDeductable");
        JSONArray productList= json.getJSONArray("productList");

        System.out.println("isDeductable ==> " + isDeductable);

        if (isDeductable) {
            //service to reduce product quantity
            for (Object o: productList) {
                JSONObject jsonObject = new JSONObject(o);
                service.reduce(UUID.fromString(jsonObject.getString("productId")), jsonObject.getInt("quantity"));
            }
        } else {
            //service to add product quantity
            for (Object o: productList) {
                JSONObject jsonObject = new JSONObject(o);
                service.increase(UUID.fromString(jsonObject.getString("productId")), jsonObject.getInt("quantity"));
            }
        }

        logger.info(String.format("$$$$ => Consumed message: %s", message));
    }
}

