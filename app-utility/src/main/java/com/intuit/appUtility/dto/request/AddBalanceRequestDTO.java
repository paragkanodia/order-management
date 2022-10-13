package com.intuit.appUtility.dto.request;

import lombok.*;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AddBalanceRequestDTO {

    private String userId;
    private Double amount;
}
