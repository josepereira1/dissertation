package com.ecommerce.qct.core.business.resources.category.category;

import com.ecommerce.qct.core.business.resources.CounterMeasure;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class Category {
    @Id
    private Long id;
    private String name;
    private Integer size;
    private List<Product> products;

    @JsonIgnore
    private String owner;
    @JsonIgnore
    private CounterMeasure countermeasure;
}
