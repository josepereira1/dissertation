package com.ecommerce.cc.outbound.adapters.messaging.cqrs.category.updateproductincategories.mapper.out;

import com.ecommerce.cc.core.business.resources.product.Product;
import com.ecommerce.cc.outbound.adapters.messaging.cqrs.category.updateproductincategories.mapper.out.dtos.ProductAndAddCategoriesAndRemoveCategoriesDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Collection;

public interface IProductAndAddCategoriesAndRemoveCategoriesDTOOutMapper {
    static ProductAndAddCategoriesAndRemoveCategoriesDTO toProductAndAddCategoriesAndRemoveCategoriesDTO(Product product, Collection<Long> currentProductCategories, Collection<Long> addCategories, Collection<Long> removeCategories) {
        ProductAndAddCategoriesAndRemoveCategoriesDTO tmp1 = new ProductAndAddCategoriesAndRemoveCategoriesDTO();
        tmp1.setId(product.getId());
        tmp1.setName(product.getName());
        try {
            tmp1.setShortDetails(new ObjectMapper().readValue(product.getShortDetails(), Object.class));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            tmp1.setShortDetails(null);
        }
        tmp1.setCurrency(product.getCurrency());
        tmp1.setDiscountPercentage(product.getDiscountPercentage());
        tmp1.setAmountInDiscount(product.getAmountInDiscount());
        tmp1.setFinalPrice(product.getFinalPrice());
        tmp1.setPn(product.getPn());
        tmp1.setStockStatus(product.getStockStatus());
        try {
            tmp1.setLinks(new ObjectMapper().readValue(product.getLinks(), Object.class));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            tmp1.setShortDetails(null);
        }
        tmp1.setVisibility(product.getVisibility());
        tmp1.setOwner(product.getOwner());
        tmp1.setCountermeasure(product.getCountermeasure());
        tmp1.setCurrentProductCategories(currentProductCategories);
        tmp1.setAddCategories(addCategories);
        tmp1.setRemoveCategories(removeCategories);
        return tmp1;
    }
}
