package com.intuit.appUtility.dto.request;

import lombok.*;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AddInventoryRequestDTO {
    private String productCode;
    private Integer quantity;
}
