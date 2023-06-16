package com.ecommerce.cc.core.business.saga.sagas.createproduct.mappers;

import com.ecommerce.cc.core.business.resources.product.Product;
import com.ecommerce.cc.core.business.saga.sagas.createproduct.mappers.dtos.ProductAndCategoryIdsDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public interface ICreateProductSagaInMapper {

    static Product toProduct(ProductAndCategoryIdsDTO product){
        Product tmp1 = new Product();
        tmp1.setId(product.getId());
        tmp1.setName(product.getName());
        try {
            tmp1.setShortDetails(new ObjectMapper().writeValueAsString(product.getShortDetails()));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            tmp1.setShortDetails(null);
        }
        tmp1.setCurrency(product.getCurrency());
        tmp1.setDiscountPercentage(product.getDiscountPercentage());
        tmp1.setAmountInDiscount(product.getAmountInDiscount());
        tmp1.setFinalPrice(product.getFinalPrice());
        try {
            tmp1.setLinks(new ObjectMapper().writeValueAsString(product.getLinks()));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            tmp1.setLinks(null);
        }
        tmp1.setVisibility(product.getVisibility());
        tmp1.setPn(product.getPn());
        return tmp1;
    }
}
