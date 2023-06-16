package com.ecommerce.qcap.core.business.logic.category.category.command;

import com.ecommerce.qcap.core.business.exceptions.EcommerceBusinessLogicException;
import com.ecommerce.qcap.core.business.resources.category.category.Category;
import com.ecommerce.qcap.core.business.resources.category.category.Product;
import com.ecommerce.qcap.core.business.logic.category.category.command.features.*;
import com.ecommerce.qcap.core.ports.in.category.ICommandCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Collection;

@Service
public class CommandCategory implements ICommandCategory {

    @Autowired
    private CreateCategory createCategory;

    @Autowired
    private UpdateCategoryName updateCategoryName;

    @Autowired
    private DeleteCategories deleteCategories;

    @Autowired
    private CreateProductInCategories createProductInCategories;

    @Autowired
    private UpdateProductInCategories updateProductInCategories;

    @Autowired
    private DeleteProductInCategories deleteProductInCategories;

    @Override
    public Category createCategories(Category category) throws EcommerceBusinessLogicException {
        return createCategory.createCategory(category);
    }

    @Override
    public Category updateCategoryName(Long id, String name) throws EcommerceBusinessLogicException{
        return updateCategoryName.updateCategoryName(id, name);
    }

    @Override
    public void deleteCategories(Collection<Long> ids) throws EcommerceBusinessLogicException{
        deleteCategories.deleteCategories(ids);
    }

    @Override
    public void createProductsInCategories(Product product, Collection<Long> categoryIds) throws EcommerceBusinessLogicException{
        createProductInCategories.createProductInCategories(product, categoryIds);
    }

    @Override
    public void updateProductInCategories(Product product, Collection<Long> categoriesIds ,Collection<Long> addCategoryIds, Collection<Long> removeCategoryIds) throws EcommerceBusinessLogicException {
        updateProductInCategories.updateProductInCategories(product, categoriesIds, addCategoryIds, removeCategoryIds);
    }

    @Override
    public void deleteProductsInCategories(String productId, Collection<Long> categoryIds) throws EcommerceBusinessLogicException{
        deleteProductInCategories.deleteProductInCategories(productId, categoryIds);
    }
}
