package com.ecommerce.sc.core.business.logic.product.query.features;

import com.ecommerce.sc.core.business.exceptions.shoppingcart.ProductWithThatIdNotExistException;
import com.ecommerce.sc.core.business.resources.Product;
import com.ecommerce.sc.core.ports.out.repository.product.IQueryProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class ReadProduct {

    @Autowired
    private IQueryProductRepository queryProductRepository;

    public Product readProduct(String productId) throws ProductWithThatIdNotExistException {
        Optional<Product> optional = queryProductRepository.findOneForUpdate(productId);
        if(!optional.isPresent()) throw ProductWithThatIdNotExistException.builder().id(productId).build();
        return optional.get();
    }
}
