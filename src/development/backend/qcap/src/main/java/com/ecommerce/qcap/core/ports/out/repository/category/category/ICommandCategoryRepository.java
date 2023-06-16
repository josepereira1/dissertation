package com.ecommerce.qcap.core.ports.out.repository.category.category;

import com.ecommerce.qcap.core.business.resources.category.category.Category;

import java.util.List;

public interface ICommandCategoryRepository {
    Category save(Category category);
    List<Category> saveAll(Iterable<Category> categories);
    void deleteAll(Iterable<Category> categories);
}
