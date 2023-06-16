package com.ecommerce.sc.inbound.rest.shoppingcart.mappers.in.dtos;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class UpdateShoppingCartFeatureShoppingCartAndProductDTO {
    @NotNull
    private Double quantity;

    @NotNull
    private UpdateShoppingCartFeatureProductDTO product;
}
