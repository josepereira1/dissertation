package com.ecommerce.sc.inbound.rest.shoppingcart.mappers.out.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class CreateShoppingCartDTO {
    private String id;
    private Integer size;
    private Double total;
}
