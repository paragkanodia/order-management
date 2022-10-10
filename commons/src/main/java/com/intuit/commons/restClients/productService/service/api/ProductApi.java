package com.intuit.commons.restClients.productService.service.api;

import com.intuit.commons.restClients.productService.request.CreateProductRequestDTO;
import com.intuit.commons.restClients.productService.request.UpdateProductRequestDTO;
import com.intuit.commons.restClients.productService.response.PriceQuoteResponseDTO;
import com.intuit.commons.restClients.productService.response.ProductResponseDTO;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.concurrent.CompletionStage;

public interface ProductApi {

    @POST("/orderManagement/products/")
    Call<ProductResponseDTO> createProduct(@Body CreateProductRequestDTO createProductRequestDTO);

    @PATCH("/orderManagement/products//{code}")
    Call<ProductResponseDTO> updateProduct(@Path("code") String code,
                                           @Body UpdateProductRequestDTO updateProductRequestDTO);

    @GET("/orderManagement/products//{code}")
    Call<ProductResponseDTO> getProduct(@Path("code") String code);

    @GET("/orderManagement/products//{code}/quote")
    public Call<PriceQuoteResponseDTO> getProductQuote(@Path("code") String code);
}
