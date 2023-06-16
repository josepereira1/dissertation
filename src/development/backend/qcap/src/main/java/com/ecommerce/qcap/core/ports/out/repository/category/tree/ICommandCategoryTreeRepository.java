package com.ecommerce.qcap.core.ports.out.repository.category.tree;

import com.ecommerce.qcap.core.business.resources.category.tree.CategoryTree;

public interface ICommandCategoryTreeRepository {
    CategoryTree save(CategoryTree categoryTree);
    void deleteById(Long id);
    void deleteCategoryTreeByName(String name);
}
