package com.ecommerce.sc.core.ports.out.repository.shoppingcartandproduct;

import com.ecommerce.sc.core.business.resources.ShoppingCartAndProduct;
import com.ecommerce.sc.core.business.resources.ShoppingCartAndProductId;
import java.util.Optional;

public interface IQueryShoppingCartAndProductRepository {
    Optional<ShoppingCartAndProduct> findById(ShoppingCartAndProductId id);
}
