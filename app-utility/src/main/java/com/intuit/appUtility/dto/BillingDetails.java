package com.intuit.appUtility.dto;

import lombok.*;

import javax.validation.constraints.NotEmpty;

@Getter
@Builder
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BillingDetails {

    @NotEmpty
    private Address billingAddress;

    private String gstNo;
}
