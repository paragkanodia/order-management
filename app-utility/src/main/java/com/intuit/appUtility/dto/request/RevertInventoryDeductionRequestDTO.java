package com.intuit.appUtility.dto.request;

import lombok.*;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RevertInventoryDeductionRequestDTO {
    private Long orderId;
    private String productCode;
}
