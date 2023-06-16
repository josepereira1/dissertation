package com.ecommerce.qp.outbound.repository.mongo.product;

import com.ecommerce.qp.core.business.resources.product.Product;
import com.ecommerce.qp.core.ports.out.repository.product.IQueryProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class QueryProductRepository implements IQueryProductRepository {

    @Autowired
    private ProductDAO productDAO;

    @Override
    public Optional<Product> findById(String id){
        return productDAO.findById(id);
    }

    @Override
    public Collection<Product> findAllByIds(List<String> ids) {
        return StreamSupport.stream(productDAO.findAllById(ids).spliterator(), false).collect(Collectors.toList());
    }

    @Override
    public boolean existsById(String id){
        return productDAO.existsById(id);
    }
}
