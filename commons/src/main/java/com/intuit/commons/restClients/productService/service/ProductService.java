package com.intuit.commons.restClients.productService.service;

import com.intuit.commons.restClients.productService.request.CreateProductRequestDTO;
import com.intuit.commons.restClients.productService.request.UpdateProductRequestDTO;
import com.intuit.commons.restClients.productService.response.PriceQuoteResponseDTO;
import com.intuit.commons.restClients.productService.response.ProductResponseDTO;

import java.util.concurrent.CompletionStage;

public interface ProductService {
     CompletionStage<ProductResponseDTO> createProduct(CreateProductRequestDTO createProductRequestDTO);

     CompletionStage<ProductResponseDTO> updateProduct(String productCode, UpdateProductRequestDTO updateProductRequestDTO);

     CompletionStage<PriceQuoteResponseDTO> getProductQuotation(String productCode);

     CompletionStage<ProductResponseDTO> getProductByCode(String code);
}
