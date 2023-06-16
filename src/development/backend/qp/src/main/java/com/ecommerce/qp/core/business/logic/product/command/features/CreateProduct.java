package com.ecommerce.qp.core.business.logic.product.command.features;

import com.ecommerce.qp.core.business.exceptions.EcommerceBusinessLogicException;
import com.ecommerce.qp.core.business.exceptions.product.ProductWithIdAlreadyExists;
import com.ecommerce.qp.core.business.resources.CounterMeasure;
import com.ecommerce.qp.core.business.resources.product.Product;
import com.ecommerce.qp.core.ports.out.repository.product.ICommandProductRepository;
import com.ecommerce.qp.core.ports.out.repository.product.IQueryProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CreateProduct {

    @Autowired
    private ICommandProductRepository commandProductRepository;

    @Autowired
    private IQueryProductRepository queryProductRepository;

    @Transactional(rollbackFor = Exception.class)
    public Product createProduct(Product product) throws EcommerceBusinessLogicException {
        if(queryProductRepository.existsById(product.getId())) throw ProductWithIdAlreadyExists.builder().id(product.getId()).build();
        return commandProductRepository.save(product);
    }
}
