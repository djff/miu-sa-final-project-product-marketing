package com.miusaatega.gatewayservice.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringCloudConfig {

    @Autowired
    private JwtAuthenticationFilter filter;

    @Bean
    public RouteLocator gatewayRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("auth" , r -> r.path("/api/auth/**")
                        .uri("lb://AUTH-SERVICE"))
                .route("account-service" , r -> r.path("/api/account/**", "/actuator/**")
                        .filters(f -> f.filter(filter))
                        .uri("lb://ACCOUNT-SERVICE"))
//                .route("payment-service", r -> r.path("/api/payment/**", "/actuator/**")
//                        .uri("lb://PAYMENT-SERVICE"))
                .route("order-service", r -> r.path("/api/orders/**")
                        .filters(f -> f.filter(filter))
                        .uri("lb://ORDER-SERVICE"))
                .route("product-service", r -> r.path("/api/product/**")
                        .filters(f -> f.filter(filter))
                        .uri("lb://PRODUCT-SERVICE"))
                .build();
    }

}