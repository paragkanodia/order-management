package com.intuit.order.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderFailedEvent {
    private Long orderNo;
}
