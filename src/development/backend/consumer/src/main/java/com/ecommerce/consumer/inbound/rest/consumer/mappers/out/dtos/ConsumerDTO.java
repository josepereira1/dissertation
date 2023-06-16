package com.ecommerce.consumer.inbound.rest.consumer.mappers.out.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class ConsumerDTO {
    private String id;
    private ContactsDTO contacts;
    private AddressDTO deliveryAddress;
    private AddressDTO chargeAddress;
    private String token;
}
