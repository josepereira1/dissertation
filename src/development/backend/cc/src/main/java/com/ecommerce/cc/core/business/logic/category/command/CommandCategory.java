package com.ecommerce.cc.core.business.logic.category.command;

import com.ecommerce.cc.core.business.exceptions.*;
import com.ecommerce.cc.core.business.logic.category.command.features.*;
import com.ecommerce.cc.core.business.resources.category.Category;
import com.ecommerce.cc.core.ports.in.category.ICommandCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Collection;

@Service
public class CommandCategory implements ICommandCategory {

    @Autowired
    private UpdateCategorySubcategories updateCategorySubcategories;

    @Autowired
    private CreateCategory createCategory;

    @Autowired
    private UpdateCategoryName updateCategoryName;

    @Autowired
    private DeleteCategory deleteCategory;

    @Override
    public Category createCategory(Category category) throws EcommerceBusinessLogicException {
        return createCategory.createCategory(category);
    }

    @Override
    public Collection<Category> updateCategorySubcategories(Long parentCategoryId, Collection<Category> subcategories) throws EcommerceBusinessLogicException {
        return updateCategorySubcategories.updateCategorySubcategories(parentCategoryId, subcategories);
    }

    @Override
    public Category updateCategoryName(Long id, Category category) throws EcommerceBusinessLogicException {
        return updateCategoryName.updateCategoryName(id, category);
    }

    @Override
    public Category deleteCategory(Long id) throws EcommerceBusinessLogicException {
        return deleteCategory.deleteCategory(id);
    }
}
