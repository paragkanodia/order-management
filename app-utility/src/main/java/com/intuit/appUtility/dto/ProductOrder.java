package com.intuit.appUtility.dto;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;

@Getter
@Builder
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductOrder {

    @NotEmpty
    private String productCode;

    @Positive
    private Integer quantity;
}
