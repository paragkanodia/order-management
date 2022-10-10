package com.intuit.commons.restClients.productService.config;

import com.intuit.commons.rest.*;
import com.intuit.commons.restClients.productService.service.ProductService;
import com.intuit.commons.restClients.productService.service.ProductServiceImpl;
import lombok.Getter;

public class ProductServiceConfiguration {
    @Getter
    private ProductService service;

    public ProductServiceConfiguration(RestClientProperties properties, HeaderProvider headerProvider) {
        RestClientUtil clientUtil = new RestClientUtil(ClientContext.builder().application("product-service").build());
        service = new ProductServiceImpl(new RetrofitConfig(properties, headerProvider).getRetrofit(), clientUtil);
    }
}
