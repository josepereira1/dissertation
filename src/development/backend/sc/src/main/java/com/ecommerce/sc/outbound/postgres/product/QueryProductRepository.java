package com.ecommerce.sc.outbound.postgres.product;

import com.ecommerce.sc.core.business.resources.Product;
import com.ecommerce.sc.core.business.resources.ShoppingCart;
import com.ecommerce.sc.core.ports.out.repository.product.IQueryProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class QueryProductRepository implements IQueryProductRepository {

    @Autowired
    private ProductDAO productDAO;

    @Override
    public Optional<Product> findById(String id) {
        return productDAO.findById(id);
    }

    @Override
    public Optional<ShoppingCart> findOneForRead(String id) {
        return productDAO.findOneForRead(id);
    }

    @Override
    public Optional<Product> findOneForUpdate(String id) {
        return productDAO.findOneForUpdate(id);
    }

    @Override
    public boolean existsById(String id) {
        return productDAO.existsById(id);
    }
}
