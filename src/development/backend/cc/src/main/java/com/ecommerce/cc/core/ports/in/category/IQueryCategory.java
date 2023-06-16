package com.ecommerce.cc.core.ports.in.category;

import com.ecommerce.cc.core.business.exceptions.EcommerceBusinessLogicException;
import com.ecommerce.cc.core.business.resources.category.Category;
import com.ecommerce.cc.core.business.resources.product.Product;
import com.ecommerce.cc.core.business.utils.Pair;

import java.util.Set;

public interface IQueryCategory {
    /*Category getCategory(Long id) throws EcommerceBusinessLogicException;
    Pair<Set<Product>,Integer> getCategoryProducts(Long id, Integer page) throws EcommerceBusinessLogicException;*/
    Category getRootCategory() throws EcommerceBusinessLogicException;
}
