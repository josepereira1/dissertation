package com.ecommerce.qcvp.core.ports.out.repository.category.category;

import com.ecommerce.qcvp.core.business.resources.CounterMeasure;
import com.ecommerce.qcvp.core.business.resources.category.category.Category;
import com.ecommerce.qcvp.core.business.resources.category.category.Visibility;

import java.util.Optional;

public interface IQueryCategoryRepository {
    Optional<Category> findById(Long id);
    Iterable<Category> findAllById(Iterable<Long> ids);
    boolean existsAllById(Iterable<Long> ids);
    Optional<Category> findCategoryProductsByPagination(Long id, int page, int productsPerPage);
    boolean existsCategoriesByName(String name);
}
