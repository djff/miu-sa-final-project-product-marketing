package edu.miu.inventoryservice.miusamsproductatega;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableCaching
@EnableEurekaClient
public class MiuSaMsProductAteApplication {
    public static void main(String[] args) {
        SpringApplication.run(MiuSaMsProductAteApplication.class, args);
    }

}
