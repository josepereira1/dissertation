package com.ecommerce.cc.core.business.logic.product.query.features;

import com.ecommerce.cc.core.business.exceptions.product.ProductWithThatIdNotExistException;
import com.ecommerce.cc.core.business.logic.product.CommonProduct;
import com.ecommerce.cc.core.business.resources.category.Category;
import com.ecommerce.cc.core.business.resources.product.Product;
import com.ecommerce.cc.core.ports.out.repository.product.IQueryProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class ReadProductCategories {

    @Autowired
    private IQueryProductRepository queryProductRepository;

    public Collection<Category> readProductCategories(String id) throws ProductWithThatIdNotExistException {
        Optional<Product> optional = queryProductRepository.findOneForRead(id);
        if(!optional.isPresent()) throw ProductWithThatIdNotExistException.builder().id(id).build();
        return optional.get().getCategories();
    }
}
