package com.ecommerce.cc.outbound.adapters.messaging.cqrs.category.createproductincategories.mapper.out;

import com.ecommerce.cc.core.business.resources.product.Product;
import com.ecommerce.cc.outbound.adapters.messaging.cqrs.category.createproductincategories.mapper.out.dtos.ProductAndCategoriesDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Collection;

public interface IProductAndCategoriesDTOOutMapper {
    static ProductAndCategoriesDTO toProductAndCategoriesDTO(Product product, Collection<Long> categoryIds){
        ProductAndCategoriesDTO tmp1 = new ProductAndCategoriesDTO();
        tmp1.setId(product.getId());
        tmp1.setName(product.getName());
        if(product.getShortDetails() != null){
            try {
                tmp1.setShortDetails(new ObjectMapper().readValue(product.getShortDetails(), Object.class));
            } catch (JsonProcessingException e) {
                tmp1.setShortDetails(null);
                e.printStackTrace();
            }
        }
        tmp1.setCurrency(product.getCurrency());
        tmp1.setDiscountPercentage(product.getDiscountPercentage());
        tmp1.setAmountInDiscount(product.getAmountInDiscount());
        tmp1.setFinalPrice(product.getFinalPrice());
        if(product.getLinks() != null) {
            try {
                tmp1.setLinks(new ObjectMapper().readValue(product.getLinks(), Object.class));
            } catch (JsonProcessingException e) {
                tmp1.setLinks(null);
                e.printStackTrace();
            }
        }
        tmp1.setVisibility(product.getVisibility());
        tmp1.setPn(product.getPn());
        tmp1.setCategories(categoryIds);
        tmp1.setOwner(product.getOwner());
        tmp1.setCountermeasure(product.getCountermeasure());
        return tmp1;
    }
}
