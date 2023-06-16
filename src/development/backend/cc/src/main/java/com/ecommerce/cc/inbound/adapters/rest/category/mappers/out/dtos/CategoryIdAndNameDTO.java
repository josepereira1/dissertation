package com.ecommerce.cc.inbound.adapters.rest.category.mappers.out.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CategoryIdAndNameDTO {
    private Long id;
    private String name;
}
