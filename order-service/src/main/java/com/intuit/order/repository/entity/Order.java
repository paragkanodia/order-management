package com.intuit.order.repository.entity;

import com.intuit.appUtility.dto.Address;
import com.intuit.appUtility.dto.BillingDetails;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import lombok.*;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Entity
@Table(schema = "public", name = "orders")
@TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
public class Order {

    @Id
    @GeneratedValue
    private Long id;

    @Type(type = "jsonb")
    @Column(columnDefinition = "jsonb")
    private BillingDetails billingDetails;

    @Type(type = "jsonb")
    @Column(columnDefinition = "jsonb")
    private Address shippingAddress;

    private String userId;
    private String productCode;
    private Integer quantity;
    private Double price;
    private String paymentNo;
    private String invoiceNo;
    private String shipmentNo;
    private String status;
    private Long createdAt;
    private Long updateAt;
}
