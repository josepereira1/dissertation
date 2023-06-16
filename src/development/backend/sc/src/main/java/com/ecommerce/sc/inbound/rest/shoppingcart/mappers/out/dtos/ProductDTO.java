package com.ecommerce.sc.inbound.rest.shoppingcart.mappers.out.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class ProductDTO {
    private String id;
    private String name;
    private String currency;
    private Double finalPrice;
    private Object links;
    private String sku;
    private String ean;
    private String pn;
}
