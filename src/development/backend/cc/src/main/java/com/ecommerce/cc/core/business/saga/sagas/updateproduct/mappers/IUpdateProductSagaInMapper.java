package com.ecommerce.cc.core.business.saga.sagas.updateproduct.mappers;

import com.ecommerce.cc.core.business.resources.product.Product;
import com.ecommerce.cc.core.business.saga.sagas.updateproduct.mappers.dtos.ProductAndAddCategoryIdsAndRemoveCategoryIdsDTO;
import com.ecommerce.cc.core.business.saga.sagas.updateproduct.resources.UpdateProductSagaProductBackup;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public interface IUpdateProductSagaInMapper {

    static Product toProduct(ProductAndAddCategoryIdsAndRemoveCategoryIdsDTO product) {
        Product tmp1 = new Product();
        tmp1.setId(product.getId());
        tmp1.setName(product.getName());
        if (product.getShortDetails() != null){ //  necessário, pois ele converte o null em string
            try {
                tmp1.setShortDetails(new ObjectMapper().writeValueAsString(product.getShortDetails()));
            } catch (JsonProcessingException e) {
                e.printStackTrace();
                tmp1.setShortDetails(null);
            }
        }
        tmp1.setCurrency(product.getCurrency());
        tmp1.setDiscountPercentage(product.getDiscountPercentage());
        tmp1.setAmountInDiscount(product.getAmountInDiscount());
        tmp1.setFinalPrice(product.getFinalPrice());
        if(product.getLinks() != null) {
            try {
                tmp1.setLinks(new ObjectMapper().writeValueAsString(product.getLinks()));
            } catch (JsonProcessingException e) {
                e.printStackTrace();
                tmp1.setLinks(null);
            }
        }
        tmp1.setVisibility(product.getVisibility());
        tmp1.setPn(product.getPn());
        return tmp1;
    }

    static UpdateProductSagaProductBackup toUpdateProductSagaProductBackup(Product product){
        UpdateProductSagaProductBackup tmp1 = new UpdateProductSagaProductBackup();
        tmp1.setProductId(product.getId());
        tmp1.setName(product.getName());
        tmp1.setShortDetails(product.getShortDetails());
        tmp1.setCurrency(product.getCurrency());
        tmp1.setDiscountPercentage(product.getDiscountPercentage());
        tmp1.setAmountInDiscount(product.getAmountInDiscount());
        tmp1.setFinalPrice(product.getFinalPrice());
        tmp1.setPn(product.getPn());
        tmp1.setStockStatus(product.getStockStatus());
        tmp1.setLinks(product.getLinks());
        tmp1.setVisibility(product.getVisibility());
        return tmp1;
    }

    static Product toProduct(UpdateProductSagaProductBackup product){
        Product tmp1 = new Product();
        tmp1.setId(product.getProductId());
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
        return tmp1;
    }
}
