package com.intuit.product.mappers;

import com.intuit.appUtility.dto.request.CreateProductRequestDTO;
import com.intuit.appUtility.dto.request.UpdateProductRequestDTO;
import com.intuit.appUtility.dto.response.PriceQuoteResponseDTO;
import com.intuit.appUtility.dto.response.ProductResponseDTO;
import com.intuit.appUtility.exception.MapstructMappingException;
import com.intuit.product.repository.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", unexpectedValueMappingException = MapstructMappingException.class)
public interface ProductMapper {

    Product toProductEntity(CreateProductRequestDTO createProductRequestDTO);

    Product toProductEntity(Long id, String code, UpdateProductRequestDTO createProductRequestDTO);

    ProductResponseDTO toProductResponseDTO(Product product);

    @Mapping(source = "product.code", target = "productCode")
    PriceQuoteResponseDTO toPriceQuote(Product product);
}
