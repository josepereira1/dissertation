package com.ecommerce.qct.core.business.logic.category.tree.command.features;

import com.ecommerce.qct.core.business.exceptions.tree.CategoryTreeIsInvalidException;
import com.ecommerce.qct.core.business.resources.category.tree.CategoryTree;
import com.ecommerce.qct.core.ports.out.repository.category.tree.ICommandCategoryTreeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UpdateCategoryTree {

    @Autowired
    private ICommandCategoryTreeRepository commandCategoryTreeRepository;

    @Transactional(rollbackFor = Exception.class)
    public CategoryTree updateCategoryTree(CategoryTree categoryTree) throws CategoryTreeIsInvalidException {
        if(!categoryTree.getName().equals("#root")) throw CategoryTreeIsInvalidException.builder().build();
        commandCategoryTreeRepository.deleteCategoryTreeByName("#root");
        return commandCategoryTreeRepository.save(categoryTree);
    }
}
