package com.ecommerce.inventory.core.business.saga.sagas.createorder.mappers.in.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class OrderDTO {
    private String id;
    private List<ProductDTO> products;
}
