package com.ecommerce.qcap.core.business.resources.category.tree;

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
public class CategoryTree {
    @Id
    private Long id;
    private String name;
    private List<CategoryTree> childCategories;
}
