package com.ecommerce.cc.core.business.logic.product.command.features;

import com.ecommerce.cc.core.business.exceptions.EcommerceBusinessLogicException;
import com.ecommerce.cc.core.business.resources.CounterMeasure;
import com.ecommerce.cc.core.business.resources.product.Product;
import com.ecommerce.cc.core.ports.out.repository.product.ICommandProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Set;

@Service
public class CreateProduct {

    @Autowired
    private ICommandProductRepository commandProductRepository;

    @Transactional(rollbackFor = Exception.class)
    public Product create(Product product, Set<Long> categoryIds) throws EcommerceBusinessLogicException {
        //if(queryProductRepository.existsProductByIdOrPn(product.getId(), product.getPn())) throw ProductWithIdOrPnAlreadyExistsException.builder().id(product.getId()).pn(product.getPn()).build();
        if(product.getCountermeasure() == null) product.setCountermeasure(CounterMeasure.UNLOCKED);
        return commandProductRepository.save(product);
    }
}
