package com.ecommerce.qcap.inbound.messaging.cqrs.category.category.updateproductincategories.mapper.in;

import com.ecommerce.qcap.core.business.resources.category.category.Product;
import com.ecommerce.qcap.inbound.messaging.cqrs.category.category.createproductincategories.mapper.in.dtos.CreateProductInCategoriesDTO;
import com.ecommerce.qcap.inbound.messaging.cqrs.category.category.updateproductincategories.mapper.in.dtos.UpdateProductInCategoriesDTO;

import java.util.Collection;

public interface IProductInMapper {
    static Product toProduct(UpdateProductInCategoriesDTO product){
        Product tmp1 = new Product();
        tmp1.setId(product.getId());
        tmp1.setName(product.getName());
        tmp1.setShortDetails(product.getShortDetails());
        tmp1.setCurrency(product.getCurrency());
        tmp1.setDiscountPercentage(product.getDiscountPercentage());
        tmp1.setAmountInDiscount(product.getAmountInDiscount());
        tmp1.setFinalPrice(product.getFinalPrice());
        tmp1.setStockStatus(product.getStockStatus());
        tmp1.setLinks(product.getLinks());
        tmp1.setVisibility(product.getVisibility());
        tmp1.setPn(product.getPn());
        tmp1.setOwner(product.getOwner());
        tmp1.setCountermeasure(product.getCountermeasure());
        return tmp1;
    }

    static Product toProduct(UpdateProductInCategoriesDTO product, Collection<Long> currentCategoryIds){
        CreateProductInCategoriesDTO tmp1 = com.ecommerce.qcap.inbound.messaging.cqrs.category.category.createproductincategories.mapper.in.IProductInMapper.toCreateProductInCategoriesDTO(product, currentCategoryIds);
        return com.ecommerce.qcap.inbound.messaging.cqrs.category.category.createproductincategories.mapper.in.IProductInMapper.toProduct(tmp1);
    }
}
