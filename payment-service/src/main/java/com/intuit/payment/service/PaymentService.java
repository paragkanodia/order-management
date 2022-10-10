package com.intuit.payment.service;

import com.intuit.appUtility.dto.request.PaymentRequestDTO;

import java.util.concurrent.CompletionStage;

public interface PaymentService {
    CompletionStage<Boolean> addPayment(PaymentRequestDTO inventoryRequestDTO);

    CompletionStage<Boolean> deductPayment(PaymentRequestDTO inventoryRequestDTO);

    CompletionStage<Double> getBalance(String userId);
}
