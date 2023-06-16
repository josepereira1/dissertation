package com.ecommerce.qp.core.business.logic.product.command.features;

import com.ecommerce.qp.core.business.exceptions.product.ProductNotExistException;
import com.ecommerce.qp.core.business.logs.ILogs;
import com.ecommerce.qp.core.business.resources.product.Product;
import com.ecommerce.qp.core.ports.out.repository.product.ICommandProductRepository;
import com.ecommerce.qp.core.ports.out.repository.product.IQueryProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

@Service
public class UpdateProduct {
    private static final Integer SECOND = 1000;
    private static final Integer WAITING_TIME = 10 * SECOND;

    @Autowired
    private ICommandProductRepository commandProductRepository;

    @Autowired
    private IQueryProductRepository queryProductRepository;

    @Autowired
    private ILogs logs;

    @Transactional(rollbackFor = Exception.class)
    public Product updateProduct(String id, Product input) throws ProductNotExistException {
        Optional<Product> optional = queryProductRepository.findById(id);
        if(!optional.isPresent()){
            try {
                logs.logWarning("entry on state of waiting time (updateProduct (CQRS))");
                Thread.sleep(WAITING_TIME);
                optional = queryProductRepository.findById(id);
                if(!optional.isPresent()) throw ProductNotExistException.builder().id(id).build();
            } catch (InterruptedException e) {
                e.printStackTrace();
                logs.logError(e.getMessage());
                return null;
            }
        }
        Product product = optional.get();
        updateProduct(input, product);
        return commandProductRepository.save(product);
    }

    private void updateProduct(Product input, Product product){
        if(input.getName() != null) product.setName(input.getName());
        if(input.getShortDetails() != null) product.setShortDetails(input.getShortDetails());
        if(input.getLongDetails() != null) product.setLongDetails(input.getLongDetails());
        if(input.getCurrency() != null) product.setCurrency(input.getCurrency());
        if(input.getInitialPrice() != null) product.setInitialPrice(input.getInitialPrice());
        if(input.getDiscountPercentage() != null) product.setDiscountPercentage(input.getDiscountPercentage());
        if(input.getAmountInDiscount() != null) product.setAmountInDiscount(input.getAmountInDiscount());
        if(input.getVatPercentage() != null) product.setVatPercentage(input.getVatPercentage());
        if(input.getAmountInVat() != null) product.setAmountInVat(input.getAmountInVat());
        if(input.getFinalPrice() != null) product.setFinalPrice(input.getFinalPrice());
        if(input.getShipping() != null) product.setShipping(input.getShipping());
        if(input.getStockStatus() != null) product.setStockStatus(input.getStockStatus());
        if(input.getLinks() != null) product.setLinks(input.getLinks());
        if(input.getVisibility() != null) product.setVisibility(input.getVisibility());
        if(input.getSku() != null) product.setSku(input.getSku());
        if(input.getEan() != null) product.setEan(input.getEan());
        if(input.getPn() != null) product.setPn(input.getPn());
        if(input.getOwner() != null) product.setOwner(input.getOwner());
        if(input.getCountermeasure() != null) product.setCountermeasure(input.getCountermeasure());
    }
}
