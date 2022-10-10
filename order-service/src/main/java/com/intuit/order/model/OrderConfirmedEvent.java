package com.intuit.order.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderConfirmedEvent {
    private Long orderNo;
    private String paymentNo;
    private String inventoryNo;
}
