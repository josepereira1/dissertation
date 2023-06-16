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
public class ReadCategoryProductsByPaginationAndVisibility {

    @Autowired
    private IQueryCategoryRepository queryCategoryRepository;

    public Category readCategoryProducts(Long id, int page, int productsPerPage, Visibility visibility) throws CategoryNotExistsException {
        Optional<Category> optional;
        if(visibility != null) optional = queryCategoryRepository.findCategoryByIdAndAndCountermeasureAndPageAndProductsPerPageAndVisibility(id, CounterMeasure.UNLOCKED, page, productsPerPage, visibility);
        else optional = queryCategoryRepository.findCategoryByIdAndCountermeasureAndPageAndProductsPerPage(id, CounterMeasure.UNLOCKED, page, productsPerPage);
        if(!optional.isPresent()) throw CategoryNotExistsException.builder().id(id).build();
        return optional.get();
    }
}
