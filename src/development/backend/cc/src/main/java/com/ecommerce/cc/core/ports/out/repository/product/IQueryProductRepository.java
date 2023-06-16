package com.ecommerce.cc.core.ports.out.repository.product;

import com.ecommerce.cc.core.business.resources.product.Product;
import java.util.List;
import java.util.Optional;

public interface IQueryProductRepository {
    Optional<Product> findById(String id);
    Optional<Product> findOneForRead(String id);
    Optional<Product> findOneForUpdate(String id);
}
