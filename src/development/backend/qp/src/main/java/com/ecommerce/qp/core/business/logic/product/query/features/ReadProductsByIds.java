package com.ecommerce.qp.core.business.logic.product.query.features;

import com.ecommerce.qp.core.business.exceptions.product.ProductNotExistException;
import com.ecommerce.qp.core.business.resources.product.Product;
import com.ecommerce.qp.core.ports.out.repository.product.IQueryProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Collection;
import java.util.List;

@Service
public class ReadProductsByIds {

    @Autowired
    private IQueryProductRepository queryProductRepository;

    public Collection<Product> getProductsById(List<String> ids) throws ProductNotExistException {
        Collection<Product> products = queryProductRepository.findAllByIds(ids);
        if(ids != null && ids.size() != products.size()) throw ProductNotExistException.builder().exceptionMessage("Some products do not exists.").build();
        return products;
    }
}
