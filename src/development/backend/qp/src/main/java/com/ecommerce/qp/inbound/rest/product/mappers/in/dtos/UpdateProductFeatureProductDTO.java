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
public class UpdateProductFeatureProductDTO {
    @Size(min = 1, max = 200)
    private String name;

    @Size(min=1, max = 200)
    private String shortDetails;

    @Valid
    private Object longDetails;

    @NotEmpty
    private String currency;

    @Min(value = 0)
    private Double initialPrice;

    @Min(value = 0)
    @Min(value = 100)
    private Double discountPercentage;

    @Min(value = 0)
    private Double amountInDiscount;

    @Min(value = 0)
    @Min(value = 100)
    private Double vatPercentage;

    @Min(value = 0)
    private Double amountInVat;

    @Min(value = 0)
    private Double finalPrice;

    @Min(value = 0)
    private Double shipping;

    private StockStatus stockStatus;

    private Object links;

    private Visibility visibility;

    @Size(min = 1)
    private String sku;

    @Size(min = 1)
    private String ean;

    @Size(min = 1)
    private String pn;

    private String owner;
    private CounterMeasure countermeasure;
}
