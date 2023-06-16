package com.ecommerce.inventory.core.business.saga.sagas.createorder.mappers.in.dtos;

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
    private Double quantity;
    /*private String sku;
    private String ean;
    private String pn;*/
}
