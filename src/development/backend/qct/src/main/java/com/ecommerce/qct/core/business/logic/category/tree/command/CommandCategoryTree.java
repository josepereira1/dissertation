package com.ecommerce.qct.core.business.logic.category.tree.command;

import com.ecommerce.qct.core.business.exceptions.EcommerceBusinessLogicException;
import com.ecommerce.qct.core.business.logic.category.tree.command.features.DeleteCategoryTree;
import com.ecommerce.qct.core.business.logic.category.tree.command.features.UpdateCategoryTree;
import com.ecommerce.qct.core.business.resources.category.tree.CategoryTree;
import com.ecommerce.qct.core.ports.in.tree.ICommandCategoryTree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommandCategoryTree implements ICommandCategoryTree {

    @Autowired
    private UpdateCategoryTree updateCategoryTree;

    @Autowired
    private DeleteCategoryTree deleteCategoryTree;

    @Override
    public CategoryTree updateCategoryTree(CategoryTree categoryTree) throws EcommerceBusinessLogicException {
        return updateCategoryTree.updateCategoryTree(categoryTree);
    }

    @Override
    public void deleteCategoryTree() {
        deleteCategoryTree.deleteCategoryTree();
    }
}
