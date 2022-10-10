package com.intuit.commons.restClients.inventoryService.config;

import com.intuit.commons.rest.*;
import com.intuit.commons.restClients.inventoryService.service.InventoryService;
import com.intuit.commons.restClients.inventoryService.service.InventoryServiceImpl;
import com.intuit.commons.restClients.productService.service.ProductService;
import com.intuit.commons.restClients.productService.service.ProductServiceImpl;
import lombok.Getter;

public class InventoryServiceConfiguration {
    @Getter
    private InventoryService service;

    public InventoryServiceConfiguration(RestClientProperties properties, HeaderProvider headerProvider) {
        RestClientUtil clientUtil = new RestClientUtil(ClientContext.builder().application("inventory-service").build());
        service = new InventoryServiceImpl(new RetrofitConfig(properties, headerProvider).getRetrofit(), clientUtil);
    }
}
