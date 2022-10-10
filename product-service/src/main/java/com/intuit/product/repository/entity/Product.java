package com.intuit.product.repository.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Entity
@Table(schema = "public", name = "products")
public class Product {

    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true,nullable = false )
    private String code;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Double price;

    @Column(nullable = false)
    private String status;

    @Column(nullable = false)
    private String category;
}
