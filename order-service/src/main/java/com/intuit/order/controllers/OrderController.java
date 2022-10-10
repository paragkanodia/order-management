package com.intuit.order.controllers;

import com.intuit.appUtility.dto.request.CreateOrderRequestDTO;
import com.intuit.appUtility.dto.response.OrderResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.concurrent.CompletionStage;

@RequestMapping("/orders")
@Validated
public interface OrderController {

    @PostMapping(produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Creates an Order", description = "This API is to create an order")
    CompletionStage<OrderResponseDTO> createOrder(
            @Valid @RequestBody CreateOrderRequestDTO createOrderRequestDTO);

    @GetMapping("/{orderNo}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Fetches order details", description = "This API is to update a product")
     CompletionStage<OrderResponseDTO> getOrder(
            @PathVariable("orderNo") String orderNo);

    @PatchMapping("/{orderNo}/cancel")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Cancels the order", description = "This API is to cancel a confirmed order")
     CompletionStage<OrderResponseDTO> cancelOrder(
            @PathVariable("orderNo") String orderNo);

}
