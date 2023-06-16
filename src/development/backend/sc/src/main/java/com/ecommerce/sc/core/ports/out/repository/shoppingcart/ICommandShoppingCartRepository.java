package com.ecommerce.sc.core.ports.out.repository.shoppingcart;

import com.ecommerce.sc.core.business.resources.ShoppingCart;

public interface ICommandShoppingCartRepository {
    ShoppingCart save(ShoppingCart shoppingCart);
    void deleteById(String id);
}
