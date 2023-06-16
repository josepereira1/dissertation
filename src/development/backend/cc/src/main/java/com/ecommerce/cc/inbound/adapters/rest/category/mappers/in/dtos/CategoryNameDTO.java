package com.ecommerce.cc.inbound.adapters.rest.category.mappers.in.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@NoArgsConstructor
@Getter
@Setter
public class CategoryNameDTO {
    @NotEmpty
    @Size(min = 1, max = 200)
    private String name;
}
