package com.intuit.appUtility.dto;

import lombok.*;

import javax.validation.constraints.NotEmpty;

@Getter
@Builder
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Address {

    @NotEmpty
    private String houseNo;
    private String streetAddress;

    @NotEmpty
    private String city;
    @NotEmpty
    private String state;
    @NotEmpty
    private String pincode;
}
