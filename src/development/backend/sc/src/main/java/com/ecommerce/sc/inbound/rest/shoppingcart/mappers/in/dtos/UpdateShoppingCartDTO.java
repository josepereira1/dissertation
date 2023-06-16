package com.ecommerce.sc.inbound.rest.shoppingcart.mappers.in.dtos;

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
public class UpdateShoppingCartDTO {

    @NotEmpty
    private String productId;

    @NotNull
    private Double quantity;
}
