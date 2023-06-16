package com.ecommerce.consumer.inbound.rest.consumer.mappers.out.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class ContactsDTO {
    private String email;
    private String mobileNumber;
}
