package com.miu.sa.orderservice.services;

import com.miu.sa.orderservice.dto.kafka.*;
import com.miu.sa.orderservice.dto.response.GenericResponse;
import com.miu.sa.orderservice.dto.response.ShippedMessageResponse;
import com.miu.sa.orderservice.models.Order;
import com.miu.sa.orderservice.repositories.OrderRepository;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class OrderService {
    final private OrderRepository orderRepository;
    final private KafkaTemplate<String, OrderProductMessage> kafkaOrderTemplate;
    final private KafkaTemplate<String, NotifyMessage> kafkaNotifyTemplate;
    final private KafkaTemplate<String, ProductMessage> kafkaProductTemplate;
    final private KafkaTemplate<String, OrderProductMessage> kafkaAffiliateTemplate;
    private final String ORDER_TOPIC = "order-topic";
    private final String AFFILIATE_TOPIC = "affiliate-topic";
    private final String NOTIFICATION_TOPIC = "notification-topic";
    private final String PRODUCT_TOPIC = "product-topic";

    public OrderService(OrderRepository orderRepository,
                        KafkaTemplate<String, OrderProductMessage> kafkaOrderTemplate,
                        KafkaTemplate<String, NotifyMessage> kafkaNotifyTemplate,
                        KafkaTemplate<String, ProductMessage> kafkaProductTemplate,
                        KafkaTemplate<String, OrderProductMessage> kafkaAffiliateTemplate) {
        this.orderRepository = orderRepository;
        this.kafkaOrderTemplate = kafkaOrderTemplate;
        this.kafkaNotifyTemplate = kafkaNotifyTemplate;
        this.kafkaProductTemplate = kafkaProductTemplate;
        this.kafkaAffiliateTemplate = kafkaAffiliateTemplate;
    }

    public GenericResponse getAllOrders(){
        var orders = orderRepository.findAll();
        var response = new GenericResponse();
        response.message = orders.size() == 0 ? "No orders found." : orders.size() + " order(s) found.";
        response.success = true;
        response.data = orders;

        return response;
    }

    public GenericResponse createNewOrder(Order order){
        order.setOrderId(UUID.randomUUID());
        orderRepository.save(order);

        // notify product topic for orders.
        var productMessage = new ProductMessage();
        productMessage.isDeductable = true;
        productMessage.productList = order.getProductList();

        kafkaProductTemplate.send(PRODUCT_TOPIC, productMessage);
        return new GenericResponse("Order Created Successfully", true, order);
    }

    @KafkaListener(topics="shipping-topic", groupId = "")
    public void setOrderAsShipped(ShippedMessageResponse shippedMessageResponse){
        var order = orderRepository.findById(shippedMessageResponse.data.orderId);
        order.ifPresent(value -> {
            value.setIsPaid(true);
            orderRepository.save(value);
            var message = "Dear " + value.getCustomerBillingInfo().getFname()+ ", this email serves to inform you that" +
                    " your order with order number: " + value.getOrderId() + " has been shipped through DHL with" +
                    "tracking number: " + shippedMessageResponse.data.trackingNumber;
            createNotifyMessage(value, message);
        });
    }

    @KafkaListener(topics = "payment-topic")
    public void setOrderAsPaid(PaymentMessage paymentMessage){
        var order = orderRepository.findById(paymentMessage.paymentResponse.orderNumber).get();

        var message = "Dear " + order.getCustomerBillingInfo().getFname() + ", here is an update to your order" +
                " payment with Order id: " + order.getOrderId() + ",\n Payment reference: " +
                paymentMessage.paymentResponse.paymentReference +
                "\n Payment Status: " + paymentMessage.responseDescription;
        createNotifyMessage(order, message);

        var orderProduct = new OrderProductMessage();
        orderProduct.orderId = order.getOrderId();
        if(paymentMessage.successful){
            // set products for shipment service
            orderProduct.productList = order.getProductList();
            kafkaOrderTemplate.send(ORDER_TOPIC, orderProduct);

            // set products for affiliate
            orderProduct.productList =
                    order.getProductList()
                            .stream()
                            .filter(p -> p.getAffiliateId() != null)
                            .collect(Collectors.toList());
            kafkaAffiliateTemplate.send(AFFILIATE_TOPIC, orderProduct);

        }
    }

    private void createNotifyMessage(Order order, String message){
        // send notify message in kafka
        var notifyMessage = new NotifyMessage();
        notifyMessage.subject = "Order has been shipped.";
        notifyMessage.message = message;
        notifyMessage.email = order.getCustomerBillingInfo().getEmail();

        // send message for notify service
        kafkaNotifyTemplate.send(NOTIFICATION_TOPIC, notifyMessage);
    }

}
