package com.intuit.appUtility.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.intuit.appUtility.dto.Address;
import com.intuit.appUtility.dto.BillingDetails;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Getter
@Builder
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateOrderRequestDTO {

    @NotBlank
    private String userId;

    @NotBlank
    private String productCode;

    @Positive
    private Integer quantity;

    private BillingDetails billingDetails;

    @NotNull
    private Address shippingAddress;

    @JsonIgnore
    private Long createdAt=System.currentTimeMillis();

    @JsonIgnore
    private Long updateAt=System.currentTimeMillis();
}
