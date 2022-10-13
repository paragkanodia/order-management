package com.intuit.commons.restClients.inventoryService.request;

import lombok.*;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DeductInventoryRequestDTO {
    private Long orderId;
    private String productCode;
    private Integer quantity;
}
