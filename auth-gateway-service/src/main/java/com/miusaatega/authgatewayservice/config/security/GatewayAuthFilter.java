package com.miusaatega.authgatewayservice.config.security;

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
public class GatewayAuthFilter implements GatewayFilter {

    @Value("${services.url.auth}")
    String authServiceUrl;

    @Autowired        // NO LONGER auto-created by Spring Cloud (see below)
    @LoadBalanced     // Explicitly request the load-balanced template with Ribbon built-in
    protected RestTemplate restTemplate;

    @Autowired
    private WebClient.Builder webClientBuilder;

    @Value("${services.token}")
    private String accountServiceToken;

    final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        ServerHttpRequest request = (ServerHttpRequest) exchange.getRequest();

        final List<String> apiEndpoints = List.of("/register", "/login");

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
//                System.out.println("token: " + token);
                HashMap<String, Object> param = new HashMap<>();
                param.put("token", token.replace("Bearer ", ""));

                return webClientBuilder.build().post().uri(authServiceUrl + "/api/auth/verify")
                        .bodyValue(param)
                        .exchangeToMono(r -> {
                            System.out.println("error: " + r);
//                            if ( r.statusCode().isError() ) { // or clientResponse.statusCode().value() >= 400
//                                return r.createException().flatMap( Mono::error);
//                            }
                            return r.bodyToMono(String.class);
                        })
                        .flatMap(r -> {
                            try {
                                JsonNode root = objectMapper.readTree(r);
                                String id = root.path("id").asText();
                                if(id != null) {
                                    exchange.getRequest().mutate().
                                            header("id", id).
                                            header("Authorization", String.format("Basic %s", accountServiceToken)).
                                            build();
                                    System.out.println("id: " + id);
                                }
                            } catch (JsonProcessingException e) {
                                ServerHttpResponse errResponse = exchange.getResponse();
                                errResponse.setStatusCode(HttpStatus.BAD_REQUEST);
                                return errResponse.setComplete();
                            }
                            return chain.filter(exchange);
                        });

            }
            catch (Exception e) {
                e.printStackTrace();
                ServerHttpResponse response = exchange.getResponse();
                response.setStatusCode(HttpStatus.BAD_REQUEST);
                return response.setComplete();
            }

        }

        return chain.filter(exchange);
    }
}
