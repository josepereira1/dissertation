package com.ecommerce.cc.core.business.logic.category.query.features;

import com.ecommerce.cc.core.business.exceptions.category.RootCategoryNotExistsException;
import com.ecommerce.cc.core.business.resources.category.Category;
import com.ecommerce.cc.core.ports.out.repository.category.IQueryCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class ReadRootCategory {

    @Autowired
    private IQueryCategoryRepository queryCategoryRepository;

    public Category readRootCategory() throws RootCategoryNotExistsException {
        Optional<Category> optional = queryCategoryRepository.findCategoryByNameIdAndNameAndChildCategories("#root");
        if(!optional.isPresent()) throw RootCategoryNotExistsException.builder().categoryName("#root").build();
        return optional.get();
    }
}
