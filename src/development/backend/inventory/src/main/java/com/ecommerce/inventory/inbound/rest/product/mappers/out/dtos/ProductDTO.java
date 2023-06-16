package com.ecommerce.inventory.inbound.rest.product.mappers.out.dtos;

import com.ecommerce.inventory.core.business.resources.StockStatus;
import com.ecommerce.inventory.core.business.resources.StockUnit;
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
    private StockUnit stockUnit;
    private Double stockQuantity;
    private StockStatus stockStatus;
}
