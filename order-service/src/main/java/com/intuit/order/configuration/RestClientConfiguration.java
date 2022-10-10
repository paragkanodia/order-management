package com.intuit.order.configuration;

import com.intuit.commons.restClients.inventoryService.config.InventoryServiceConfiguration;
import com.intuit.commons.restClients.inventoryService.service.InventoryService;
import com.intuit.commons.restClients.paymentService.config.PaymentServiceConfiguration;
import com.intuit.commons.restClients.paymentService.service.PaymentService;
import com.intuit.commons.restClients.productService.config.ProductServiceConfiguration;
import com.intuit.commons.restClients.productService.service.ProductService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RestClientConfiguration {

    @Bean
    public ProductService getProductService(){
        return new ProductServiceConfiguration(()->"http://localhost:8871/",null).getService();
    }

    @Bean
    public InventoryService getInventoryService(){
        return new InventoryServiceConfiguration(()->"http://localhost:8873/",null).getService();
    }

    @Bean
    public PaymentService getPaymentService(){
        return new PaymentServiceConfiguration(()->"http://localhost:8874/",null).getService();
    }
}
