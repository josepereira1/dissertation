package com.ecommerce.sc.inbound.rest.shoppingcart.mappers.out.dtos;

import java.util.Set;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class ShoppingCartDTO {
    private String id;
    private Integer size;
    private Double total;
    private Set<ShoppingCartAndProductDTO> products;
}
