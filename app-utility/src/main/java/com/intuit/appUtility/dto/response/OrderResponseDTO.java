package com.intuit.appUtility.dto.response;

import com.intuit.appUtility.enums.OrderStatus;
import lombok.*;

@Builder
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class OrderResponseDTO {

    private String userId;
    private String orderId;
    private String productCode;
    private Double price;
    private Integer quantity;
    private String paymentNo;
    private String invoiceNo;
    private String shipmentNo;
    private Long createdAt;
    private Long updateAt;
    private OrderStatus status;
}
