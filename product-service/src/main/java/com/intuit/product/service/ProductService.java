package com.intuit.product.service;

import com.intuit.appUtility.dto.request.CreateProductRequestDTO;
import com.intuit.appUtility.dto.request.UpdateProductRequestDTO;
import com.intuit.appUtility.dto.response.PriceQuoteResponseDTO;
import com.intuit.appUtility.dto.response.ProductResponseDTO;

import java.util.concurrent.CompletionStage;

public interface ProductService {

     CompletionStage<ProductResponseDTO> createProduct(CreateProductRequestDTO createProductRequestDTO);

     CompletionStage<ProductResponseDTO> updateProduct(String productCode, UpdateProductRequestDTO updateProductRequestDTO);

     CompletionStage<PriceQuoteResponseDTO> getProductQuotation(String productCode);

     CompletionStage<ProductResponseDTO> getProductByCode(String code);
}
