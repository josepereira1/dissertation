package com.ecommerce.sc.core.ports.in.shoppingcart;

import com.ecommerce.sc.core.business.exceptions.EcommerceBusinessLogicException;
import com.ecommerce.sc.core.business.exceptions.shoppingcart.ShoppingCartNotExistsException;
import com.ecommerce.sc.core.business.resources.ShoppingCart;
import com.ecommerce.sc.core.business.resources.ShoppingCartAndProduct;
import java.util.Set;

public interface IQueryShoppingCart {
    ShoppingCart readShoppingCart(String id) throws EcommerceBusinessLogicException;
    Set<ShoppingCartAndProduct> readShoppingCartProducts(String shoppingCartId) throws EcommerceBusinessLogicException;
    Integer readShoppingCartSize(String id) throws EcommerceBusinessLogicException;
}
