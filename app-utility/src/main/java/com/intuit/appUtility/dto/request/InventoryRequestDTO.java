package com.intuit.appUtility.dto.request;

import lombok.*;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class InventoryRequestDTO {
    private Long orderId;
    private String productCode;
    private Integer quantity;
}
