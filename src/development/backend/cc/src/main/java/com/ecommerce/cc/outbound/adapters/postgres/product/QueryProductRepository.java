package com.ecommerce.cc.outbound.adapters.postgres.product;

import com.ecommerce.cc.core.business.resources.product.Product;
import com.ecommerce.cc.core.ports.out.repository.product.IQueryProductRepository;
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
    public Optional<Product> findOneForRead(String id) {
        return productDAO.findOneForRead(id);
    }

    @Override
    public Optional<Product> findOneForUpdate(String id) {
        return productDAO.findOneForUpdate(id);
    }
}
