package com.intuit.commons.restClients.productService.response;

import com.intuit.commons.restClients.productService.request.ProductCategory;
import com.intuit.commons.restClients.productService.request.Status;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ProductResponseDTO {

    private String code;
    private String name;
    private Double price;
    private ProductCategory category;
    private Status status;
}
