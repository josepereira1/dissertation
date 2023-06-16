package com.ecommerce.qcvp.core.business.logic.category.category.query.features;

import com.ecommerce.qcvp.core.business.exceptions.category.CategoryNotExistsException;
import com.ecommerce.qcvp.core.business.resources.category.category.Category;
import com.ecommerce.qcvp.core.ports.out.repository.category.category.IQueryCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class ReadCategoryProducts {

    @Autowired
    private IQueryCategoryRepository queryCategoryRepository;

    public Category readCategoryProducts(Long id, int page, int productsPerPage) throws CategoryNotExistsException {
        Optional<Category> optional = queryCategoryRepository.findCategoryProductsByPagination(id, ((page - 1) * productsPerPage), productsPerPage);
        if(!optional.isPresent()) throw CategoryNotExistsException.builder().id(id).build();
        return optional.get();
    }
}
