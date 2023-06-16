package com.ecommerce.consumer.inbound.rest.consumer.mappers.in.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class CreateConsumerFeatureClientDTO {

    @NotEmpty
    private String id;

    @NotEmpty
    private String password;

    @NotNull
    @Valid
    private CreateConsumerFeatureContactsDTO contacts;

    @Valid
    private CreateConsumerFeatureAddressDTO deliveryAddress;

    @Valid
    private CreateConsumerFeatureAddressDTO chargeAddress;
}
