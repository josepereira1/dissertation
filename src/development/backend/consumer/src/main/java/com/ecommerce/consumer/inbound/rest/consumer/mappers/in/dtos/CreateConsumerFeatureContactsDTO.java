package com.ecommerce.consumer.inbound.rest.consumer.mappers.in.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import javax.validation.constraints.NotEmpty;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class CreateConsumerFeatureContactsDTO {

    @NotEmpty
    private String email;
    private String mobileNumber;
}
