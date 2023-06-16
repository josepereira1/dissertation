package com.ecommerce.sc.core.ports.in.shoppingcart;

import com.ecommerce.sc.core.business.exceptions.EcommerceBusinessLogicException;
import com.ecommerce.sc.core.business.resources.ShoppingCart;
import com.ecommerce.sc.core.business.resources.ShoppingCartAndProduct;
import java.util.Set;

public interface ICommandShoppingCart {
    ShoppingCart createShoppingCart(String shoppingCartId) throws EcommerceBusinessLogicException;
    ShoppingCart updateShoppingCart(String shoppingCartId, Set<ShoppingCartAndProduct> products) throws EcommerceBusinessLogicException;
    ShoppingCart deleteShoppingCartProducts(String shoppingCartId) throws EcommerceBusinessLogicException;
}
