package com.ecommerce.sc.core.ports.out.repository.shoppingcartandproduct;

import com.ecommerce.sc.core.business.resources.ShoppingCartAndProduct;

public interface ICommandShoppingCartAndProductRepository {
    Iterable<ShoppingCartAndProduct> saveAll(Iterable<ShoppingCartAndProduct> shoppingCartAndProducts);
    void deleteAll(Iterable<ShoppingCartAndProduct> shoppingCartAndProducts);

}
