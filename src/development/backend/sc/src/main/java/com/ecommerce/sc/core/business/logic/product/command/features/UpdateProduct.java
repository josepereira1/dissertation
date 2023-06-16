package com.ecommerce.sc.core.business.logic.product.command.features;

import com.ecommerce.sc.core.business.exceptions.shoppingcart.ProductWithThatIdNotExistException;
import com.ecommerce.sc.core.business.resources.Product;
import com.ecommerce.sc.core.ports.out.repository.product.ICommandProductRepository;
import com.ecommerce.sc.core.ports.out.repository.product.IQueryProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

@Service
public class UpdateProduct {

    @Autowired
    private IQueryProductRepository queryProductRepository;

    @Autowired
    private ICommandProductRepository commandProductRepository;

    @Transactional(rollbackFor = Exception.class)
    public Product updateProduct(String productId, Product product) throws ProductWithThatIdNotExistException {
        Optional<Product> optional = queryProductRepository.findOneForUpdate(productId);
        if(!optional.isPresent()) throw ProductWithThatIdNotExistException.builder().id(productId).build();
        Product currentProduct = optional.get();
        currentProduct = updateProduct(product, currentProduct);
        return commandProductRepository.save(currentProduct);
    }

    private Product updateProduct(Product productUpdated, Product currentProduct){
        if(productUpdated.getName() != null) currentProduct.setName(productUpdated.getName());
        if(productUpdated.getCurrency() != null) currentProduct.setCurrency(productUpdated.getCurrency());
        if(productUpdated.getFinalPrice() != null) currentProduct.setFinalPrice(productUpdated.getFinalPrice());
        if(productUpdated.getLinks() != null) currentProduct.setLinks(productUpdated.getLinks());
        if(productUpdated.getVisibility() != null) currentProduct.setVisibility(productUpdated.getVisibility());
        if(productUpdated.getSku() != null) currentProduct.setSku(productUpdated.getSku());
        if(productUpdated.getEan() != null) currentProduct.setEan(productUpdated.getEan());
        if(productUpdated.getPn() != null) currentProduct.setPn(productUpdated.getPn());
        if(productUpdated.getOwner() != null) currentProduct.setOwner(productUpdated.getOwner());
        if(productUpdated.getCountermeasure() != null) currentProduct.setCountermeasure(productUpdated.getCountermeasure());
        return currentProduct;
    }
}
