package com.ecommerce.consumer.core.business.resources;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class Address {
    private String address;
    private String city;
    private String postalCode;
    private String country;
}
