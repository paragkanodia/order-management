package com.intuit.commons.restClients.paymentService.service;

import com.intuit.commons.restClients.paymentService.request.PaymentRequestDTO;

import java.util.concurrent.CompletionStage;

public interface PaymentService {

    CompletionStage<Boolean> deductPayment(PaymentRequestDTO paymentRequestDTO);
    CompletionStage<Boolean> revertPayment(PaymentRequestDTO paymentRequestDTO);
}
