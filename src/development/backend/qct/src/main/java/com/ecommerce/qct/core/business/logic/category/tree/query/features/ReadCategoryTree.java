package com.ecommerce.qct.core.business.logic.category.tree.query.features;

import com.ecommerce.qct.core.business.exceptions.tree.CategoryTreeNotExistsException;
import com.ecommerce.qct.core.business.resources.category.tree.CategoryTree;
import com.ecommerce.qct.core.ports.out.repository.category.tree.IQueryCategoryTreeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class ReadCategoryTree {

    @Autowired
    private IQueryCategoryTreeRepository queryCategoryTreeRepository;

    public CategoryTree readCategoryTree() throws CategoryTreeNotExistsException {
        Optional<CategoryTree> optional = queryCategoryTreeRepository.findCategoryTreeByName("#root");
        if(!optional.isPresent()) throw CategoryTreeNotExistsException.builder().build();
        return optional.get();
    }
}
