package com.intuit.appUtility.dto.request;

import lombok.*;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DeductPaymentRequestDTO {

    private Long orderId;
    private String userId;
    private Double amount;
}
