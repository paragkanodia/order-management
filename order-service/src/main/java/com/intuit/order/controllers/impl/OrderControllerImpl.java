package com.intuit.order.controllers.impl;

import com.intuit.appUtility.dto.request.CreateOrderRequestDTO;
import com.intuit.appUtility.dto.response.OrderResponseDTO;
import com.intuit.order.controllers.OrderController;
import com.intuit.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletionStage;

@RestController
public class OrderControllerImpl implements OrderController {

    @Autowired
    private OrderService orderService;

    @Override
    public CompletionStage<OrderResponseDTO> createOrder(CreateOrderRequestDTO createOrderRequestDTO) {
        return orderService.createOrder(createOrderRequestDTO);
    }

    @Override
    public CompletionStage<OrderResponseDTO> getOrder(String orderNo) {
        return orderService.getOrder(orderNo);
    }

    @Override
    public CompletionStage<OrderResponseDTO> cancelOrder(String orderNo) {
        return null;
    }

    @Override
    public CompletionStage<Boolean> retryOrder(String orderNo) {
        return orderService.retryOrder(orderNo);
    }
}
