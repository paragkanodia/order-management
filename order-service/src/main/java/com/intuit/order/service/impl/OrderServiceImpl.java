package com.intuit.order.service.impl;

import com.intuit.appUtility.dto.request.CreateOrderRequestDTO;
import com.intuit.appUtility.dto.response.OrderResponseDTO;
import com.intuit.appUtility.enums.OrderStatus;
import com.intuit.commons.restClients.productService.service.ProductService;
import com.intuit.order.mappers.OrderMapper;
import com.intuit.order.model.OrderConfirmedEvent;
import com.intuit.order.model.OrderCreatedEvent;
import com.intuit.order.model.OrderFailedEvent;
import com.intuit.order.repository.OrderRepository;
import com.intuit.order.repository.entity.Order;
import com.intuit.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private ProductService productService;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private KafkaTemplate<String, Object> template;

    private String orderCreatedTopic = "ORDER_CREATED";

    @Override
    public CompletionStage<OrderResponseDTO> createOrder(CreateOrderRequestDTO createOrderRequestDTO) {
        return productService.getProductQuotation(createOrderRequestDTO.getProductCode())
                .thenApply(priceQuoteResponseDTO -> orderRepository.save(orderMapper.toOrderModel(createOrderRequestDTO, priceQuoteResponseDTO, OrderStatus.CREATED)))
                .thenApply(order -> {
                    sendOrderCreatedEvent(order);
                    return orderMapper.toOrderResponseDTOFromOrder(order);
                        });
    }

    @Override
    public CompletionStage<OrderResponseDTO> getOrder(String orderNo) {
        return CompletableFuture.supplyAsync(()->orderRepository.findById(Long.valueOf(orderNo)))
                .thenApply(order -> orderMapper.toOrderResponseDTOFromOrder(order.get()));
    }

    @Override
    public CompletionStage<OrderResponseDTO> retryOrder(String orderNo) {
        return CompletableFuture.supplyAsync(()->orderRepository.findById(Long.valueOf(orderNo)))
                .thenApply(order -> {
                    if(order.isPresent() && (order.get().getStatus().equals("CREATED") || order.get().getStatus().equals("FAILED")))
                        sendOrderCreatedEvent(order.get());
                    return orderMapper.toOrderResponseDTOFromOrder(order.get());
                });
    }

    private void sendOrderCreatedEvent(Order order){
        OrderCreatedEvent orderCreatedEvent=OrderCreatedEvent.builder()
                .userId(order.getUserId())
                .orderNo(order.getId())
                .productCode(order.getProductCode())
                .shippingAddress(order.getShippingAddress())
                .billingDetails(order.getBillingDetails())
                .createdAt(order.getCreatedAt())
                .quantity(order.getQuantity())
                .price(order.getPrice())
                .build();

        template.send(orderCreatedTopic,orderCreatedEvent);
    }

    @KafkaListener(topics = "ORDER_CONFIRMED", groupId = "mygroup")
    public void consumeOrderMessage(OrderConfirmedEvent message)
    {
        Order order = orderRepository.findById(message.getOrderNo()).get();
        order.setStatus(OrderStatus.CONFIRMED.toString());
        orderRepository.save(order);
    }

    @KafkaListener(topics = "ORDER_FAILED", groupId = "mygroup")
    public void consumeOrderMessage(OrderFailedEvent message)
    {
        Order order = orderRepository.findById(message.getOrderNo()).get();
        order.setStatus(OrderStatus.FAILED.toString());
        orderRepository.save(order);
    }
}
