package com.ecommerce.consumer.inbound.rest.consumer.mappers.out.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class AddressDTO {
    private String address;
    private String city;
    private String postalCode;
    private String country;
}
