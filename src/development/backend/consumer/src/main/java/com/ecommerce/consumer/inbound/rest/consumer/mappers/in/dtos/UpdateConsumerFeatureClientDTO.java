package com.ecommerce.consumer.inbound.rest.consumer.mappers.in.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import javax.validation.Valid;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class UpdateConsumerFeatureClientDTO {

    private String password;

    @Valid
    private UpdateConsumerFeatureContactsDTO contacts;

    @Valid
    private UpdateConsumerFeatureAddressDTO deliveryAddress;

    @Valid
    private UpdateConsumerFeatureAddressDTO chargeAddress;
}
