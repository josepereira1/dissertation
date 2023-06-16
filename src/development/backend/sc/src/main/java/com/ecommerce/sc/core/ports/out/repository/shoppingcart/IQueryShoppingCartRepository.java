package com.ecommerce.sc.core.ports.out.repository.shoppingcart;

import com.ecommerce.sc.core.business.resources.ShoppingCart;
import java.util.Optional;

public interface IQueryShoppingCartRepository {
    Optional<ShoppingCart> findOneForRead(String id);
    Optional<ShoppingCart> findOneForUpdate(String id);
    Optional<Integer> findShoppingCartSize(String id);
    boolean existsById(String id);
}
