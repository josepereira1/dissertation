package com.ecommerce.qcvp.inbound.messaging.cqrs.category.category.createproductincategories.mapper.in;

import com.ecommerce.qcvp.core.business.resources.category.category.Product;
import com.ecommerce.qcvp.inbound.messaging.cqrs.category.category.createproductincategories.mapper.in.dtos.CreateProductInCategoriesDTO;

public interface IProductInMapper {
    static Product toProduct(CreateProductInCategoriesDTO product){
        Product tmp1 = new Product();
        tmp1.setId(product.getId());
        tmp1.setName(product.getName());
        tmp1.setShortDetails(product.getShortDetails());
        tmp1.setCurrency(product.getCurrency());
        tmp1.setDiscountPercentage(product.getDiscountPercentage());
        tmp1.setAmountInDiscount(product.getAmountInDiscount());
        tmp1.setFinalPrice(product.getFinalPrice());
        tmp1.setLinks(product.getLinks());
        tmp1.setVisibility(product.getVisibility());
        tmp1.setPn(product.getPn());
        tmp1.setOwner(product.getOwner());
        tmp1.setCountermeasure(product.getCountermeasure());
        return tmp1;
    }
}
