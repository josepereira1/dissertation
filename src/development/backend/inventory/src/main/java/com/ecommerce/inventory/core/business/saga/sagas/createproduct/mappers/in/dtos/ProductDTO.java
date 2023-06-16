package com.ecommerce.inventory.core.business.saga.sagas.createproduct.mappers.in.dtos;

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

    //private StockUnit stockUnit;
    //private Double stockQuantity;

    private String sku;
    private String ean;
    private String pn;
}
