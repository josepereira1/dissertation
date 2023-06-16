package com.ecommerce.qct.outbound.repository.mongo.category.tree;

import com.ecommerce.qct.core.business.resources.category.tree.CategoryTree;
import com.ecommerce.qct.core.ports.out.repository.category.tree.ICommandCategoryTreeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommandCategoryTreeRepository implements ICommandCategoryTreeRepository {

    @Autowired
    private CategoryTreeDAO categoryTreeDAO;

    @Override
    public CategoryTree save(CategoryTree categoryTree) {
        return categoryTreeDAO.save(categoryTree);
    }

    @Override
    public void deleteById(Long id) {
        categoryTreeDAO.deleteById(id);
    }

    @Override
    public void deleteCategoryTreeByName(String name) {
        categoryTreeDAO.deleteCategoryTreeByName(name);
    }
}
