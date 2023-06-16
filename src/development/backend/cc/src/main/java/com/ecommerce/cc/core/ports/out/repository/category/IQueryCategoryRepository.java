package com.ecommerce.cc.core.ports.out.repository.category;

import com.ecommerce.cc.core.business.resources.category.Category;
import java.util.Optional;

public interface IQueryCategoryRepository {
    Optional<Category> findById(Long id);
    Optional<Category> findOneForRead(String id);
    Optional<Category> findOneForUpdate(Long id);
    boolean existsByName(String name);
    Optional<Category> findCategoryByNameIdAndNameAndChildCategories(String name);
}
