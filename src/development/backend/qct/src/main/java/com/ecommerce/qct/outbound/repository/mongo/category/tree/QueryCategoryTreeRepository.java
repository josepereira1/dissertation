package com.ecommerce.qct.outbound.repository.mongo.category.tree;

import com.ecommerce.qct.core.business.resources.category.tree.CategoryTree;
import com.ecommerce.qct.core.ports.out.repository.category.tree.IQueryCategoryTreeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class QueryCategoryTreeRepository implements IQueryCategoryTreeRepository {

    @Autowired
    private CategoryTreeDAO categoryTreeDAO;

    @Override
    public Optional<CategoryTree> findCategoryTreeByName(String name) {
        return categoryTreeDAO.findCategoryTreeByName(name);
    }

    @Override
    public boolean existsCategoryTreeByName(String name) {
        return categoryTreeDAO.existsCategoryTreeByName(name);
    }
}
