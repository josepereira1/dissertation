package com.ecommerce.cc.core.ports.out.repository.category;

import com.ecommerce.cc.core.business.resources.category.Category;

public interface ICommandCategoryRepository {
    Category save(Category category);
    Iterable<Category> saveAll(Iterable<Category> categories);
    void delete(Category category);
}
