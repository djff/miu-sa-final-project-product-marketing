package edu.miu.sa.paymentservice.processor;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.miu.sa.paymentservice.dto.BasicResponse;
import edu.miu.sa.paymentservice.dto.Card;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RefreshScope
public class CardService {
    @Value("${PAYMENT.CARD:http://BANK-SERVICE}")
    String baseUrl;
    //@Value("${services.token}")
    private String accountServiceToken;

    private final String path = "/api/card/pay";

    @Autowired
    @LoadBalanced
    protected RestTemplate restTemplate;

    final ObjectMapper objectMapper = new ObjectMapper();

    public BasicResponse payByCard(Card request){
        BasicResponse response = new BasicResponse(false);
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.setBasicAuth(accountServiceToken);

            HttpEntity<Card> entity = new HttpEntity(request, headers);
            //response = restTemplate.postForObject(baseUrl + path, request, BasicResponse.class);
            //response = restTemplate.postForObject(baseUrl + path, entity, BasicResponse.class);
            var url = String.format("?cardNumber=%s&nameOnCard=%s&expDate=%s&amount=%f",
                    request.getCardNumber(), request.getNameOnCard(), request.getExpDate(), request.getAmount());
            System.out.println(baseUrl + path + url);
            String responseStr = restTemplate.exchange(baseUrl + path + url, HttpMethod.GET, entity, String.class).getBody();

            JsonNode root = objectMapper.readTree(responseStr);

            response.setSuccessful(root.path("successful").asBoolean());
            response.setResponseCode(root.path("responseCode").asText());
            response.setResponseDescription(root.path("responseDescription").asText());

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return response;

    }
}
