package com.intuit.commons.restClients.productService.service;

import com.intuit.commons.rest.RestClientUtil;
import com.intuit.commons.restClients.productService.request.CreateProductRequestDTO;
import com.intuit.commons.restClients.productService.request.UpdateProductRequestDTO;
import com.intuit.commons.restClients.productService.response.PriceQuoteResponseDTO;
import com.intuit.commons.restClients.productService.response.ProductResponseDTO;
import com.intuit.commons.restClients.productService.service.api.ProductApi;
import retrofit2.Retrofit;

import java.util.concurrent.CompletionStage;

public class ProductServiceImpl implements ProductService{

    private ProductApi productApi;
    private RestClientUtil restClientUtil;

    public ProductServiceImpl(Retrofit retrofit, RestClientUtil restClientUtil)
    {
        this.productApi=retrofit.create(ProductApi.class);
        this.restClientUtil=restClientUtil;
    }

    @Override
    public CompletionStage<ProductResponseDTO> createProduct(CreateProductRequestDTO createProductRequestDTO) {
        return restClientUtil.toCompletableFuture(productApi.createProduct(createProductRequestDTO));
    }

    @Override
    public CompletionStage<ProductResponseDTO> updateProduct(String productCode, UpdateProductRequestDTO updateProductRequestDTO) {
        return restClientUtil.toCompletableFuture(productApi.updateProduct(productCode,updateProductRequestDTO));
    }

    @Override
    public CompletionStage<PriceQuoteResponseDTO> getProductQuotation(String productCode) {
        return restClientUtil.toCompletableFuture(productApi.getProductQuote(productCode));
    }

    @Override
    public CompletionStage<ProductResponseDTO> getProductByCode(String code) {
        return restClientUtil.toCompletableFuture(productApi.getProduct(code));
    }
}
