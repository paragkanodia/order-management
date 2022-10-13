package com.intuit.appUtility.dto.request;

import lombok.*;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RevertPaymentRequestDTO {

    private Long orderId;
    private String userId;
}
