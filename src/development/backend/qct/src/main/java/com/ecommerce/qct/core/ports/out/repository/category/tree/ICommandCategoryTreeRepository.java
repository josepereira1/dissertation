package com.ecommerce.qct.core.ports.out.repository.category.tree;

import com.ecommerce.qct.core.business.resources.category.tree.CategoryTree;

public interface ICommandCategoryTreeRepository {
    CategoryTree save(CategoryTree categoryTree);
    void deleteById(Long id);
    void deleteCategoryTreeByName(String name);
}
