package com.intuit.product.controllers;

import com.intuit.appUtility.dto.request.CreateProductRequestDTO;
import com.intuit.appUtility.dto.request.UpdateProductRequestDTO;
import com.intuit.appUtility.dto.response.PriceQuoteResponseDTO;
import com.intuit.appUtility.dto.response.ProductResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.concurrent.CompletionStage;

@RequestMapping("/products")
@Validated
public interface ProductController {

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Creates a Product", description = "This API is to create a product")
    public CompletionStage<ProductResponseDTO> createProduct(
            @RequestBody @Valid CreateProductRequestDTO createProductRequestDTO);

    @PatchMapping("/{code}")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Updates a Product", description = "This API is to update a product")
    public CompletionStage<ProductResponseDTO> updateProduct(
            @PathVariable("code") String code,
            @Valid @RequestBody UpdateProductRequestDTO updateProductRequestDTO);

    @GetMapping("/{code}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Retrieves a Product", description = "This API is to retrieve a product by code")
    public CompletionStage<ProductResponseDTO> getProduct(
            @PathVariable("code") String code);

    @GetMapping("/{code}/quote")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Returns a Product Quote", description = "This API is to retrieve a product quote")
    public CompletionStage<PriceQuoteResponseDTO> getProductQuote(
            @PathVariable("code") String code);
}
