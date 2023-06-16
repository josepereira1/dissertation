package com.ecommerce.co.inbound.rest.order.mappers.in.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class CreateOrderProductDTO {

    @JsonProperty("id")
    @NotNull
    @NotEmpty
    private String id;

    @JsonProperty("name")
    @NotNull
    @NotEmpty
    private String name;

    @JsonProperty("quantity")
    @NotNull
    @Min(value = 1)
    private Integer quantity;

    @JsonProperty("currency")
    @NotNull
    private String currency;

    @JsonProperty("initialPrice")
    @NotNull
    @Min(value = 0)
    private Double initialPrice;

    @JsonProperty("discountPercentage")
    @NotNull
    @Min(value = 0)
    private Double discountPercentage;

    @JsonProperty("amountInDiscount")
    @NotNull
    @Min(value = 0)
    private Double amountInDiscount;

    @JsonProperty("vatPercentage")
    @NotNull
    @Min(value = 0)
    private Double vatPercentage;

    @JsonProperty("amountInVat")
    @NotNull
    @Min(value = 0)
    private Double amountInVat;

    @JsonProperty("finalPrice")
    @NotNull
    @Min(value = 0)
    private Double finalPrice;

    @JsonProperty("shipping")
    @NotNull
    @Min(value = 0)
    private Double shipping;

    @JsonProperty("sku")
    @NotNull
    @NotEmpty
    private String sku;

    @JsonProperty("ean")
    @NotNull
    @NotEmpty
    private String ean;

    @JsonProperty("pn")
    @NotNull
    @NotEmpty
    private String pn;
}
