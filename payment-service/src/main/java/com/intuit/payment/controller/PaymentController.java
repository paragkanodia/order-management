package com.intuit.payment.controller;

import com.intuit.appUtility.dto.request.PaymentRequestDTO;
import com.intuit.payment.service.PaymentService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletionStage;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    private PaymentService service;

    @PostMapping("/deduct")
    @Operation(summary = "deducts user balance against an order", description = "This api deducts user balance against an order")
    public CompletionStage<Boolean> deduct(@RequestBody final PaymentRequestDTO requestDTO){
        return this.service.deductPayment(requestDTO);
    }

    @PostMapping("/add")
    @Operation(summary = "reverts the balance deduction against an order", description = "This api reverts the balance deduction against an order")
    public CompletionStage<Boolean> add(@RequestBody final PaymentRequestDTO requestDTO){
        return this.service.addPayment(requestDTO);
    }

    @GetMapping("/{userId}")
    @Operation(summary = "Returns existing balance of user", description = "Returns existing balance of user")
    public CompletionStage<Double> getBalance(@PathVariable("userId") String userId){
        return this.service.getBalance(userId);
    }

}
