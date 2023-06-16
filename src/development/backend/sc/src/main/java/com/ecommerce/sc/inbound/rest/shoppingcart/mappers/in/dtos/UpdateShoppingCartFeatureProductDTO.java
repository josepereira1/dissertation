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
public class UpdateShoppingCartFeatureProductDTO {
    @NotNull
    private String id;

    @NotNull
    private String name;

    @NotNull
    private Double finalPrice;

    @NotNull
    private String sku;

    @NotNull
    private String ean;

    @NotNull
    private String pn;
}
