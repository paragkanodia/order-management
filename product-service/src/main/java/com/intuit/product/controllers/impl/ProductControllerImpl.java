package com.intuit.product.controllers.impl;

import com.intuit.appUtility.dto.request.CreateProductRequestDTO;
import com.intuit.appUtility.dto.request.UpdateProductRequestDTO;
import com.intuit.appUtility.dto.response.PriceQuoteResponseDTO;
import com.intuit.appUtility.dto.response.ProductResponseDTO;
import com.intuit.product.controllers.ProductController;
import com.intuit.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletionStage;

@RestController
public class ProductControllerImpl implements ProductController {

    @Autowired
    private ProductService productService;

    @Override
    public CompletionStage<ProductResponseDTO> createProduct(CreateProductRequestDTO createProductRequestDTO) {
        return productService.createProduct(createProductRequestDTO);
    }

    @Override
    public CompletionStage<ProductResponseDTO> updateProduct(String code, UpdateProductRequestDTO updateProductRequestDTO) {
        return productService.updateProduct(code, updateProductRequestDTO);
    }

    @Override
    public CompletionStage<ProductResponseDTO> getProduct(String code) {
        return productService.getProductByCode(code);
    }

    @Override
    public CompletionStage<PriceQuoteResponseDTO> getProductQuote(String code) {
        return productService.getProductQuotation(code);
    }
}
