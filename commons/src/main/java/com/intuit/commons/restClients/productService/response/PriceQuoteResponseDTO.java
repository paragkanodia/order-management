package com.intuit.commons.restClients.productService.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PriceQuoteResponseDTO {
    private String productCode;
    private Double price;
}
