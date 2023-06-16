package com.ecommerce.qp.core.ports.in.product;

import com.ecommerce.qp.core.business.exceptions.EcommerceBusinessLogicException;
import com.ecommerce.qp.core.business.resources.product.Product;
import com.ecommerce.qp.core.business.resources.product.Visibility;

import java.util.Collection;
import java.util.List;

public interface IQueryProduct {
    Product readProduct(String id) throws EcommerceBusinessLogicException;
    Product readVisibleAndNotVisibleProduct(String id) throws EcommerceBusinessLogicException;
    Collection<Product> readProductsByIds(List<String> ids) throws EcommerceBusinessLogicException;
}
