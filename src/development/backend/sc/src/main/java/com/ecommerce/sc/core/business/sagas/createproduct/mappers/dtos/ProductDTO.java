package com.ecommerce.sc.core.business.sagas.createproduct.mappers.dtos;

import com.ecommerce.sc.core.business.resources.Visibility;
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
    private Visibility visibility;
    private String sku;
    private String ean;
    private String pn;
}
