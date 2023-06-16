package com.ecommerce.qcap.core.ports.in.category;

import com.ecommerce.qcap.core.business.exceptions.EcommerceBusinessLogicException;
import com.ecommerce.qcap.core.business.resources.category.category.Category;
import com.ecommerce.qcap.core.business.resources.category.category.Product;
import java.util.Collection;

public interface ICommandCategory {
    Category createCategories(Category category) throws EcommerceBusinessLogicException;
    Category updateCategoryName(Long id, String name) throws EcommerceBusinessLogicException;
    void deleteCategories(Collection<Long> ids) throws EcommerceBusinessLogicException;
    void createProductsInCategories(Product product, Collection<Long> categoryIds) throws EcommerceBusinessLogicException;
    void updateProductInCategories(Product product, Collection<Long> categoriesIds, Collection<Long> addCategoryIds, Collection<Long> removeCategoryIds) throws EcommerceBusinessLogicException;
    void deleteProductsInCategories(String productId, Collection<Long> categoryIds) throws EcommerceBusinessLogicException;

}
