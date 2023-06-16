package com.ecommerce.qp.core.business.logic.product.query.features;

import com.ecommerce.qp.core.business.exceptions.product.ProductIsBlockedException;
import com.ecommerce.qp.core.business.exceptions.product.ProductNotVisibleException;
import com.ecommerce.qp.core.business.exceptions.product.ProductNotExistException;
import com.ecommerce.qp.core.business.resources.CounterMeasure;
import com.ecommerce.qp.core.business.resources.product.Product;
import com.ecommerce.qp.core.business.resources.product.Visibility;
import com.ecommerce.qp.core.ports.out.repository.product.IQueryProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class ReadProduct {

    @Autowired
    private IQueryProductRepository queryProductRepository;

    public Product readProduct(String id) throws ProductNotExistException, ProductNotVisibleException, ProductIsBlockedException {
        Optional<Product> optional = queryProductRepository.findById(id);
        if(!optional.isPresent()) throw ProductNotExistException.builder().id(id).build();
        Product product = optional.get();
        if(product.getCountermeasure() != null && product.getCountermeasure().equals(CounterMeasure.BLOCKED)) throw ProductIsBlockedException.builder().id(id).build();
        if(product.getVisibility() != null && product.getVisibility().equals(Visibility.NOT_VISIBLE)) throw ProductNotVisibleException.builder().id(id).build();
        return product;
    }
}
