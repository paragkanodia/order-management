package com.intuit.appUtility.dto.response;

import com.intuit.appUtility.enums.ProductCategory;
import com.intuit.appUtility.enums.Status;
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
