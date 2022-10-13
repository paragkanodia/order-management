package com.intuit.commons.restClients.paymentService.service;

import com.intuit.commons.restClients.paymentService.request.DeductPaymentRequestDTO;
import com.intuit.commons.restClients.paymentService.request.PaymentRequestDTO;
import com.intuit.commons.restClients.paymentService.request.RevertPaymentRequestDTO;

import java.util.concurrent.CompletionStage;

public interface PaymentService {

    CompletionStage<Boolean> deductPayment(DeductPaymentRequestDTO paymentRequestDTO);
    CompletionStage<Boolean> revertPayment(RevertPaymentRequestDTO paymentRequestDTO);
}
