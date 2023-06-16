package com.ecommerce.co.inbound.rest.order.mappers.in.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class CreateOrderAddressDTO {

    @JsonProperty("address")
    @NotNull
    @NotEmpty
    private String address;

    @JsonProperty("city")
    @NotNull
    @NotEmpty
    private String city;

    @JsonProperty("postalCode")
    @NotNull
    @NotEmpty
    private String postalCode;

    @JsonProperty("country")
    @NotNull
    @NotEmpty
    private String country;
}
