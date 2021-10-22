package com.miusaatega.gatewayservice.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.List;
import java.util.function.Predicate;

@Component
public class JwtAuthenticationFilter implements GatewayFilter {

    @Autowired
    private JwtUtil jwtUtil;

    @Value("${services.account.token}")
    private String accountServiceToken;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        ServerHttpRequest request = (ServerHttpRequest) exchange.getRequest();

        final List<String> apiEndpoints = List.of("/register", "/login", "/orders", "/payments", "/products", "api/affiliates/productMarkup");

        Predicate<ServerHttpRequest> isApiSecured = r -> apiEndpoints.stream()
                .noneMatch(uri -> r.getURI().getPath().contains(uri));

        if (isApiSecured.test(request)) {
            if (!request.getHeaders().containsKey("Authorization")) {
                ServerHttpResponse response = exchange.getResponse();
                response.setStatusCode(HttpStatus.UNAUTHORIZED);

                return response.setComplete();
            }

            final String token = request.getHeaders().getOrEmpty("Authorization").get(0);

            try {
                String id = jwtUtil.extractUsername(token);
                exchange.getRequest().mutate().
                        header("id", id).
                        header("TOKEN", accountServiceToken).
                        build();
                System.out.println("id: " + id);


                return chain.filter(exchange);

            }
            catch (Exception e) {
                e.printStackTrace();
                ServerHttpResponse response = exchange.getResponse();
                response.setStatusCode(HttpStatus.BAD_REQUEST);
                return response.setComplete();
            }

        }


        exchange.getRequest().mutate().
                header("TOKEN", accountServiceToken).
                build();

        return chain.filter(exchange);
    }
}
