package com.ecommerce.qcap.core.business.logic.category.category.query.features;

import com.ecommerce.qcap.core.business.exceptions.category.CategoryNotExistsException;
import com.ecommerce.qcap.core.business.resources.CounterMeasure;
import com.ecommerce.qcap.core.business.resources.category.category.Category;
import com.ecommerce.qcap.core.business.resources.category.category.Visibility;
import com.ecommerce.qcap.core.ports.out.repository.category.category.IQueryCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class ReadVisibleCategoryProductsByPagination {

    @Autowired
    private IQueryCategoryRepository queryCategoryRepository;

    public Category readVisibleCategoryProductsByPagination(Long id, int page, int productsPerPage) throws CategoryNotExistsException {
        Optional<Category> optional = queryCategoryRepository.findCategoryByIdAndAndCountermeasureAndPageAndProductsPerPageAndVisibility(id, CounterMeasure.UNLOCKED, page, productsPerPage, Visibility.VISIBLE);
        if(!optional.isPresent()) throw CategoryNotExistsException.builder().id(id).build();
        return optional.get();
    }
}
