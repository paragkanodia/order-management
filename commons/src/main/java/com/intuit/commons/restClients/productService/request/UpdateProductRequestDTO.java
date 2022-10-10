package com.intuit.commons.restClients.productService.request;

import lombok.*;

@Getter
@Builder
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateProductRequestDTO {
    private String name;
    private Double price;
    private Status status;
    private ProductCategory category;
}
