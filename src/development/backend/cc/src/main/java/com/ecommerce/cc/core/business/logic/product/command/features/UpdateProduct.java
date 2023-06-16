package com.ecommerce.cc.core.business.logic.product.command.features;

import com.ecommerce.cc.core.business.exceptions.EcommerceBusinessLogicException;
import com.ecommerce.cc.core.business.exceptions.product.ProductWithThatIdNotExistException;
import com.ecommerce.cc.core.business.logic.product.CommonProduct;
import com.ecommerce.cc.core.business.logic.version.command.CommandVersion;
import com.ecommerce.cc.core.business.resources.Version;
import com.ecommerce.cc.core.business.resources.product.Product;
import com.ecommerce.cc.core.ports.out.messaging.cqrs.category.ICQRSUpdateProductInCategories;
import com.ecommerce.cc.core.ports.out.repository.product.ICommandProductRepository;
import com.ecommerce.cc.core.ports.out.repository.product.IQueryProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.*;

@Service
public class UpdateProduct {

    @Autowired
    private ICommandProductRepository commandProductRepository;

    @Autowired
    private IQueryProductRepository queryProductRepository;

    @Autowired
    private ICQRSUpdateProductInCategories cqrsUpdateProductInCategories;

    @Autowired
    private CommandVersion commandVersion;

    @Transactional(rollbackFor = Exception.class)
    public Product updateProduct(String id, Product product) throws EcommerceBusinessLogicException {
        Optional<Product> optional = queryProductRepository.findOneForUpdate(id);
        if(!optional.isPresent()) throw ProductWithThatIdNotExistException.builder().id(id).build();
        Product tmp = optional.get();
        Collection<Long> currentCategories = CommonProduct.getCategoriesIds(tmp.getCategories());
        updateProduct(product, tmp);
        tmp = commandProductRepository.save(tmp);
        if((currentCategories != null && !currentCategories.isEmpty())) {
            Version categoriesVersion;
            for(Long categoryId : currentCategories){
                categoriesVersion = commandVersion.generateVersion("c" + categoryId);
                cqrsUpdateProductInCategories.synchronize(categoriesVersion, tmp, currentCategories, new TreeSet<>(), new TreeSet<>());
            }
        }
        return tmp;
    }

    private Product updateProduct(Product productUpdated, Product product) {
        if(productUpdated.getName() != null) product.setName(productUpdated.getName());
        if(productUpdated.getShortDetails() != null) product.setShortDetails(productUpdated.getShortDetails());
        if(productUpdated.getCurrency() != null) product.setCurrency(productUpdated.getCurrency());
        if(productUpdated.getDiscountPercentage() != null) product.setDiscountPercentage(productUpdated.getDiscountPercentage());
        if(productUpdated.getAmountInDiscount() != null) product.setAmountInDiscount(productUpdated.getAmountInDiscount());
        if(productUpdated.getFinalPrice() != null) product.setFinalPrice(productUpdated.getFinalPrice());
        if(productUpdated.getStockStatus() != null) product.setStockStatus(productUpdated.getStockStatus());
        if(productUpdated.getLinks() != null) product.setLinks(productUpdated.getLinks());
        if(productUpdated.getVisibility() != null) product.setVisibility(productUpdated.getVisibility());
        if(productUpdated.getPn() != null) product.setPn(productUpdated.getPn());
        if(productUpdated.getOwner() != null) product.setOwner(productUpdated.getOwner());
        if(productUpdated.getCountermeasure() != null) product.setCountermeasure(productUpdated.getCountermeasure());
        return product;
    }
}
