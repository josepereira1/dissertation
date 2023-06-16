package com.ecommerce.sc.core.ports.out.repository.product;

import com.ecommerce.sc.core.business.resources.Product;
import com.ecommerce.sc.core.business.resources.ShoppingCart;

import java.util.Optional;

public interface IQueryProductRepository {
    Optional<Product> findById(String id);
    Optional<ShoppingCart> findOneForRead(String id);
    Optional<Product> findOneForUpdate(String id);
    boolean existsById(String id);
}
