package com.intuit.commons.restClients.paymentService.config;

import com.intuit.commons.rest.*;
import com.intuit.commons.restClients.inventoryService.service.InventoryService;
import com.intuit.commons.restClients.inventoryService.service.InventoryServiceImpl;
import com.intuit.commons.restClients.paymentService.service.PaymentService;
import com.intuit.commons.restClients.paymentService.service.PaymentServiceImpl;
import lombok.Getter;

public class PaymentServiceConfiguration {
    @Getter
    private PaymentService service;

    public PaymentServiceConfiguration(RestClientProperties properties, HeaderProvider headerProvider) {
        RestClientUtil clientUtil = new RestClientUtil(ClientContext.builder().application("payment-service").build());
        service = new PaymentServiceImpl(new RetrofitConfig(properties, headerProvider).getRetrofit(), clientUtil);
    }
}
