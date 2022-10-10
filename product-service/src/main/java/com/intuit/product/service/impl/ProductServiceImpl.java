package com.intuit.product.service.impl;

import com.intuit.appUtility.dto.request.CreateProductRequestDTO;
import com.intuit.appUtility.dto.request.UpdateProductRequestDTO;
import com.intuit.appUtility.dto.response.PriceQuoteResponseDTO;
import com.intuit.appUtility.dto.response.ProductResponseDTO;
import com.intuit.commons.exception.ExceptionCodes;
import com.intuit.commons.utils.ValidationHelper;
import com.intuit.product.mappers.ProductMapper;
import com.intuit.product.repository.ProductRepository;
import com.intuit.product.repository.entity.Product;
import com.intuit.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private CacheManager productsCacheManager;

    @Override
    public CompletionStage<ProductResponseDTO> createProduct(CreateProductRequestDTO createProductRequestDTO) {
        return CompletableFuture.supplyAsync(() ->
                productRepository.save(productMapper.toProductEntity(createProductRequestDTO)))
                .thenApply(product -> {
                    productsCacheManager.getCache("productsCache").put(product.getCode(),product);
                    return productMapper.toProductResponseDTO(product);
                });
    }

    @Override
    public CompletionStage<ProductResponseDTO> updateProduct(String productCode, UpdateProductRequestDTO updateProductRequestDTO) {

        return CompletableFuture.supplyAsync(() -> findProductByCode(productCode))
                .thenApply(product -> productRepository.save(productMapper.toProductEntity(product.getId(),product.getCode(), updateProductRequestDTO)))
                .thenApply(product -> {
                    productsCacheManager.getCache("productsCache").put(product.getCode(),product);
                    return productMapper.toProductResponseDTO(product);
                });
    }

    @Override
    public CompletionStage<PriceQuoteResponseDTO> getProductQuotation(String productCode) {
        return CompletableFuture.supplyAsync(() -> findProductByCode(productCode))
                .thenApply(productMapper::toPriceQuote);
    }

    @Override
    public CompletionStage<ProductResponseDTO> getProductByCode(String code) {
        return CompletableFuture.supplyAsync(() -> findProductByCode(code))
                .thenApply(productMapper::toProductResponseDTO);
    }

    private Product findProductByCode(String code){
        Product product=null;
        product=(Product) productsCacheManager.getCache("productsCache").get(code).get();
        if(Objects.isNull(product))
         product = productRepository.findByCode(code);

        ValidationHelper.notNull(product, ExceptionCodes.V101,"Product with code: "+code+" not found");
        return product;
    }
}
