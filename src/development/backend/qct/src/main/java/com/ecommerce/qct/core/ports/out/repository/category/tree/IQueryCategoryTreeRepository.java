package com.ecommerce.qct.core.ports.out.repository.category.tree;

import com.ecommerce.qct.core.business.resources.category.tree.CategoryTree;
import java.util.Optional;

public interface IQueryCategoryTreeRepository {
    Optional<CategoryTree> findCategoryTreeByName(String name);
    boolean existsCategoryTreeByName(String name);
}
