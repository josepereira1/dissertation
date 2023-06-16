package com.ecommerce.inventory.core.business.logic.product.query.features;

import com.ecommerce.inventory.core.business.exceptions.product.ProductWithThatIdNotExistException;
import com.ecommerce.inventory.core.business.resources.Product;
import com.ecommerce.inventory.core.ports.out.repository.product.IQueryProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class ReadProduct {

    @Autowired
    private IQueryProductRepository queryProductRepository;

    public Product readProduct(String id) throws ProductWithThatIdNotExistException {
        Optional<Product> optional = queryProductRepository.findById(id);
        if(!optional.isPresent()) throw ProductWithThatIdNotExistException.builder().id(id).build();
        return optional.get();
    }
}
