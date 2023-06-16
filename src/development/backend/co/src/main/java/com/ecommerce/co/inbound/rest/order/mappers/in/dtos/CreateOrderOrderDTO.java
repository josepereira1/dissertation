package com.ecommerce.co.inbound.rest.order.mappers.in.dtos;

import com.ecommerce.co.core.business.resources.CounterMeasure;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class CreateOrderOrderDTO {

    @JsonProperty("clientId")
    @NotNull
    @NotEmpty
    //  client info
    private String clientId;

    @JsonProperty("tin")
    @Size(min = 9, max = 9)
    private String tin;

    @JsonProperty("products")
    @NotNull
    @Valid
    private List<CreateOrderProductDTO> products;

    @JsonProperty("chargeAddress")
    @NotNull
    @Valid
    private CreateOrderAddressDTO chargeAddress;

    @JsonProperty("deliveryAddress")
    @NotNull
    @Valid
    private CreateOrderAddressDTO deliveryAddress;

    @JsonProperty("other")
    private Object other;

    @JsonProperty("owner")
    private String owner;

    @JsonProperty("countermeasure")
    private CounterMeasure countermeasure;
}
