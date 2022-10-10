package com.intuit.order.model;

import com.intuit.appUtility.dto.Address;
import com.intuit.appUtility.dto.BillingDetails;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderCreatedEvent {

    private String userId;
    private Long orderNo;
    private String productCode;
    private Integer quantity;
    private Double price;
    private BillingDetails billingDetails;
    private Address shippingAddress;
    private Long createdAt;
    private Long updateAt;
}
