package com.ecommerce.inventory.inbound.rest.product.mappers.in.dtos;

import com.ecommerce.inventory.core.business.resources.StockUnit;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class CreateProductFeatureProductDTO {

    @NotNull
    @NotEmpty
    private String id;
    @NotNull
    @NotEmpty
    private String sku;
    @NotNull
    @NotEmpty
    private String ean;
    @NotNull
    @NotEmpty
    private String pn;

    @NotNull
    @Min(value = 0)
    private Double stockQuantity;

    @NotNull
    private StockUnit stockUnit;
}
