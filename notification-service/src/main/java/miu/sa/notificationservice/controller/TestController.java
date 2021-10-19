package miu.sa.notificationservice.controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import miu.sa.notificationservice.service.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

@RestController
@RequestMapping("publish")
public class TestController {

    private final Producer producer;

    public TestController(Producer producer) {
        this.producer = producer;
    }

    Gson g = new Gson();
    @PostMapping("notify")
    public ResponseEntity<Void> publishOrder(@RequestBody Object o){
        System.out.println("logging... " + g.toJson(o));
        producer.publishToOrderTopic(g.toJson(o));
        return ResponseEntity.ok().build();
    }

    @Autowired
    RestTemplate restTemplate;

    @GetMapping("")
    public String helloWorld() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("TOKEN", "YXBpLXNlcnZpY2UtYWNjb3VudDpwd2QkMjAyMQ==");
        HttpEntity<String> httpEntity = new HttpEntity<>("some body", headers);

        ResponseEntity<String> response = restTemplate.exchange("http://localhost:8282/api/customer", HttpMethod.GET, httpEntity, String.class);
        return "<h1>Hello from the " + response.getBody() + "!</h1>";
    }

}
