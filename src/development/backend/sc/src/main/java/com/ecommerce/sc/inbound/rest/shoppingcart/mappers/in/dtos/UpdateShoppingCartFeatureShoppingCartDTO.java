package com.ecommerce.sc.inbound.rest.shoppingcart.mappers.in.dtos;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class UpdateShoppingCartFeatureShoppingCartDTO {
    @NotNull
    private String id;
    private Set<UpdateShoppingCartFeatureShoppingCartAndProductDTO> products;
}
