package com.ecommerce.qcap.core.ports.out.repository.category.category;

import com.ecommerce.qcap.core.business.resources.CounterMeasure;
import com.ecommerce.qcap.core.business.resources.category.category.Category;
import com.ecommerce.qcap.core.business.resources.category.category.Visibility;

import java.util.Optional;

public interface IQueryCategoryRepository {
    Optional<Category> findById(Long id);
    Iterable<Category> findAllById(Iterable<Long> ids);
    boolean existsAllById(Iterable<Long> ids);
    Optional<Category> findCategoryByIdAndCountermeasureAndPageAndProductsPerPage(Long id, CounterMeasure counterMeasure, int page, int productsPerPage);
    Optional<Category> findCategoryByIdAndAndCountermeasureAndPageAndProductsPerPageAndVisibility(Long id, CounterMeasure counterMeasure, int page, int productsPerPage, Visibility visibility);
    boolean existsCategoriesByName(String name);
}
