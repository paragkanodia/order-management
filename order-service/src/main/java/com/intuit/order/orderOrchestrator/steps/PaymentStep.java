package com.intuit.order.orderOrchestrator.steps;

import com.intuit.commons.restClients.paymentService.request.DeductPaymentRequestDTO;
import com.intuit.commons.restClients.paymentService.request.PaymentRequestDTO;
import com.intuit.commons.restClients.paymentService.request.RevertPaymentRequestDTO;
import com.intuit.commons.restClients.paymentService.service.PaymentService;
import com.intuit.order.orderOrchestrator.WorkflowStep;
import com.intuit.order.orderOrchestrator.WorkflowStepStatus;

public class PaymentStep implements WorkflowStep {

    private final PaymentService paymentService;
    private final PaymentRequestDTO requestDTO;
    private WorkflowStepStatus stepStatus = WorkflowStepStatus.PENDING;

    public PaymentStep(PaymentService paymentService, PaymentRequestDTO requestDTO) {
        this.paymentService = paymentService;
        this.requestDTO = requestDTO;
    }

    @Override
    public WorkflowStepStatus getStatus() {
        return this.stepStatus;
    }

    @Override
    public Boolean process() {
        Boolean result = paymentService.deductPayment(DeductPaymentRequestDTO.builder()
                        .amount(requestDTO.getAmount())
                        .orderId(requestDTO.getOrderId())
                        .userId(requestDTO.getUserId())
                .build()).toCompletableFuture().join();
        if(result==false)
        {
            this.stepStatus=WorkflowStepStatus.FAILED;
            return false;
        }
        this.stepStatus=WorkflowStepStatus.COMPLETE;
        return true;
    }

    @Override
    public Boolean revert() {
        return paymentService.revertPayment(RevertPaymentRequestDTO.builder()
                        .orderId(requestDTO.getOrderId())
                        .userId(requestDTO.getUserId())
                .build()).toCompletableFuture().join();
    }

}
