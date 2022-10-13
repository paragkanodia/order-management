package com.intuit.order.service;

import com.intuit.appUtility.dto.request.CreateOrderRequestDTO;
import com.intuit.appUtility.dto.response.OrderResponseDTO;

import java.util.concurrent.CompletionStage;

public interface OrderService {

    CompletionStage<OrderResponseDTO> createOrder(CreateOrderRequestDTO createOrderRequestDTO);
    CompletionStage<OrderResponseDTO> getOrder(String orderNo);
    CompletionStage<OrderResponseDTO> retryOrder(String orderNo);
}
