package com.intuit.payment.service;

import com.intuit.appUtility.dto.request.AddBalanceRequestDTO;
import com.intuit.appUtility.dto.request.DeductPaymentRequestDTO;
import com.intuit.appUtility.dto.request.RevertPaymentRequestDTO;

import java.util.concurrent.CompletionStage;

public interface PaymentService {
    CompletionStage<Boolean> addBalance(AddBalanceRequestDTO inventoryRequestDTO);

    CompletionStage<Boolean> revertPaymentDeduction(RevertPaymentRequestDTO inventoryRequestDTO);

    CompletionStage<Boolean> deductPayment(DeductPaymentRequestDTO inventoryRequestDTO);

    CompletionStage<Double> getBalance(String userId);
}
