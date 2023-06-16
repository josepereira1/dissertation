package com.ecommerce.sc.core.business.sagas.createorder.mappers.out.dtos;

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
    private Double quantity;
}
