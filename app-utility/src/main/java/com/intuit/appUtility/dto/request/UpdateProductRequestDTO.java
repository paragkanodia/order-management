package com.intuit.appUtility.dto.request;

import com.intuit.appUtility.enums.ProductCategory;
import com.intuit.appUtility.enums.Status;
import com.intuit.appUtility.validation.annotation.ValidEnum;
import lombok.*;

import javax.validation.constraints.NotEmpty;

@Getter
@Builder
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateProductRequestDTO {

    @NotEmpty
    private String name;

    private Double price;

    @ValidEnum(enumClass = Status.class)
    private Status status;

    @ValidEnum(enumClass = ProductCategory.class)
    private ProductCategory category;
}
