package com.ecommerce.qcvp.core.business.logic.category.category.command.features;

import com.ecommerce.qcvp.core.business.exceptions.category.CategoryNotExistsException;
import com.ecommerce.qcvp.core.business.resources.category.category.Category;
import com.ecommerce.qcvp.core.business.resources.category.category.Product;
import com.ecommerce.qcvp.core.ports.out.repository.category.category.ICommandCategoryRepository;
import com.ecommerce.qcvp.core.ports.out.repository.category.category.IQueryCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;

@Service
public class CreateProductInCategories2 {

    @Autowired
    private ICommandCategoryRepository commandCategoryRepository;

    @Autowired
    private IQueryCategoryRepository queryCategoryRepository;

    @Autowired
    private CreateProductInCategories createProductInCategories;

    @Autowired
    private DeleteProductInCategories deleteProductInCategories;

    @Transactional(rollbackFor = Exception.class)
    public void createProductInCategories(Product product, Collection<Long> addCategoryIds, Collection<Long> removeCategoryIds) throws CategoryNotExistsException {
        if(addCategoryIds != null && addCategoryIds.size() > 0) createProductInCategories.createProductInCategories(product, addCategoryIds);
        if(removeCategoryIds != null && removeCategoryIds.size() > 0) deleteProductInCategories.deleteProductInCategories(product.getId(), removeCategoryIds);
    }
}
