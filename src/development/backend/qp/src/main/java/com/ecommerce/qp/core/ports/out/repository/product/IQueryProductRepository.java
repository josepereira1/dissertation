package com.ecommerce.qp.core.ports.out.repository.product;

import com.ecommerce.qp.core.business.resources.product.Product;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface IQueryProductRepository {
    Optional<Product> findById(String id);
    Collection<Product> findAllByIds(List<String> ids);
    boolean existsById(String id);
}
