package com.ecommerce.cc.inbound.adapters.rest.category.mappers.out;

import com.ecommerce.cc.core.business.resources.category.Category;
import com.ecommerce.cc.core.business.resources.product.Product;
import com.ecommerce.cc.inbound.adapters.rest.category.mappers.out.dtos.CategoryDTO;
import com.ecommerce.cc.inbound.adapters.rest.category.mappers.out.dtos.CategoryIdAndNameAndChildCategoriesDTO;
import com.ecommerce.cc.inbound.adapters.rest.category.mappers.out.dtos.CategoryIdAndNameDTO;
import com.ecommerce.cc.inbound.adapters.rest.category.mappers.out.dtos.ProductIdAndNameAndPriceAndDiscountAndStockStatusDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.HashSet;
import java.util.stream.Collectors;

public interface ICategoryOutMapper {
    static CategoryIdAndNameAndChildCategoriesDTO toCategoryIdAndNameChildCategoriesDTO(Category category){
        CategoryIdAndNameAndChildCategoriesDTO tmp1 = new CategoryIdAndNameAndChildCategoriesDTO();
        tmp1.setId(category.getId());
        tmp1.setName(category.getName());
        HashSet<CategoryIdAndNameAndChildCategoriesDTO> childCategories = new HashSet();
        if(category.getChildCategories() != null)
            for (Category elem : category.getChildCategories())
                childCategories.add(toCategoryIdAndNameChildCategoriesDTO(elem));
        tmp1.setChildCategories(childCategories);
        return tmp1;
    }

    /*static CategoryDTO toCategoryDTO(Category category){
        CategoryDTO tmp1 = new CategoryDTO();
        tmp1.setId(category.getId());
        tmp1.setName(category.getName());
        HashSet<CategoryDTO> childCategories = new HashSet();
        if(category.getChildCategories() != null)
            for (Category elem : category.getChildCategories())
                childCategories.add(toCategoryDTO(elem));
        tmp1.setChildCategories(childCategories);
        tmp1.setProducts(category.getProducts().stream().map(ICategoryOutMapper::toProductProductAndNameAndPriceAndDiscountAndStockStatusDTO).collect(Collectors.toSet()));
        return tmp1;
    }*/

    static ProductIdAndNameAndPriceAndDiscountAndStockStatusDTO toProductProductAndNameAndPriceAndDiscountAndStockStatusDTO(Product product) {
        ProductIdAndNameAndPriceAndDiscountAndStockStatusDTO tmp1 = new ProductIdAndNameAndPriceAndDiscountAndStockStatusDTO();
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
        tmp1.setDiscountPercentage(product.getDiscountPercentage());
        tmp1.setFinalPrice(product.getFinalPrice());
        tmp1.setStockStatus(product.getStockStatus());
        try {
            tmp1.setLinks(new ObjectMapper().readValue(product.getLinks(), Object.class));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        tmp1.setVisibility(product.getVisibility());
        tmp1.setPn(product.getPn());
        return tmp1;
    }

    static CategoryIdAndNameDTO toCategoryIdAndNameDTO(Category category){
        CategoryIdAndNameDTO tmp1 = new CategoryIdAndNameDTO();
        tmp1.setId(category.getId());
        tmp1.setName(category.getName());
        return tmp1;
    }

}
