package com.ecommerce.cc.inbound.adapters.rest.category.mappers.out.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class CategoryDTO {
    private Long id;
    private String name;
    private Set<CategoryDTO> childCategories;
    private Set<ProductIdAndNameAndPriceAndDiscountAndStockStatusDTO> products;
}
