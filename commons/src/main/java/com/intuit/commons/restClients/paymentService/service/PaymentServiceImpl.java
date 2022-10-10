package com.intuit.commons.restClients.paymentService.service;

import com.intuit.commons.rest.RestClientUtil;
import com.intuit.commons.restClients.paymentService.request.PaymentRequestDTO;
import com.intuit.commons.restClients.paymentService.service.api.PaymentApi;
import retrofit2.Retrofit;

import java.util.concurrent.CompletionStage;

public class PaymentServiceImpl implements PaymentService{

    private RestClientUtil restClientUtil;
    private PaymentApi paymentApi;

    public PaymentServiceImpl(Retrofit retrofit, RestClientUtil restClientUtil)
    {
        this.restClientUtil=restClientUtil;
        paymentApi=retrofit.create(PaymentApi.class);
    }

    @Override
    public CompletionStage<Boolean> deductPayment(PaymentRequestDTO paymentRequestDTO) {
        return restClientUtil.toCompletableFuture(paymentApi.deductPayment(paymentRequestDTO));
    }

    @Override
    public CompletionStage<Boolean> revertPayment(PaymentRequestDTO paymentRequestDTO) {
        return restClientUtil.toCompletableFuture(paymentApi.revertPayment(paymentRequestDTO));
    }
}
