package com.ecommerce.qp.inbound.rest.product.mappers.in.dtos;

import com.ecommerce.qp.core.business.resources.CounterMeasure;
import com.ecommerce.qp.core.business.resources.product.StockStatus;
import com.ecommerce.qp.core.business.resources.product.Visibility;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import javax.validation.Valid;
import javax.validation.constraints.*;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class CreateProductFeatureProductDTO {
    @NotNull
    @Min(value = 0)
    private String id;

    @NotNull
    @Size(min = 1, max = 200)
    private String name;

    @NotNull
    @Size(min=1, max = 200)
    private String shortDetails;

    @NotNull
    @Valid
    private Object longDetails;

    @NotNull
    @NotEmpty
    private String currency;

    @NotNull
    @Min(value = 0)
    private Double initialPrice;

    @NotNull
    @Min(value = 0)
    @Min(value = 100)
    private Double discountPercentage;

    @NotNull
    @Min(value = 0)
    private Double amountInDiscount;

    @NotNull
    @Min(value = 0)
    @Min(value = 100)
    private Double vatPercentage;

    @NotNull
    @Min(value = 0)
    private Double amountInVat;

    @NotNull
    @Min(value = 0)
    private Double finalPrice;

    @NotNull
    @Min(value = 0)
    private Double shipping;

    private StockStatus stockStatus;

    private Object links;

    @NotNull
    private Visibility visibility;

    @NotNull
    @Size(min = 1)
    private String sku;             //  SKU (Stock keeping unit)

    @NotNull
    @Size(min = 1)
    private String ean;             //  EAN (International Article Number)

    @NotNull
    @Size(min = 1)
    private String pn;              //  PN  (brand id)


    private String owner;
    private CounterMeasure countermeasure;
}
