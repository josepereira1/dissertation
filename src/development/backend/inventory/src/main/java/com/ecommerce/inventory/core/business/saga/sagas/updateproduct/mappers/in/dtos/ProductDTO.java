package com.ecommerce.inventory.core.business.saga.sagas.updateproduct.mappers.in.dtos;

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

    private String sku;
    private String ean;
    private String pn;
}
