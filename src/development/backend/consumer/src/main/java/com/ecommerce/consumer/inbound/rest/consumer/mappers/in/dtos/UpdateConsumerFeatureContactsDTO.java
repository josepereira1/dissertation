package com.ecommerce.consumer.inbound.rest.consumer.mappers.in.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class UpdateConsumerFeatureContactsDTO {
    private String email;
    private String mobileNumber;
}
