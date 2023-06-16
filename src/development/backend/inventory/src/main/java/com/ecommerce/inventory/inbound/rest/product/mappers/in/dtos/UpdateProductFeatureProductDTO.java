package com.ecommerce.inventory.inbound.rest.product.mappers.in.dtos;

import com.ecommerce.inventory.core.business.resources.StockUnit;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import javax.validation.constraints.Min;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class UpdateProductFeatureProductDTO {
    private StockUnit stockUnit;
    @Min(value = 0)
    private Double stockQuantity;
}
