package com.ecommerce.cp.core.business.saga.sagas.updateproduct.mappers.in;

import com.ecommerce.cp.core.business.resources.product.Product;
import com.ecommerce.cp.core.business.saga.sagas.updateproduct.mappers.in.dtos.ProductDTO;
import com.ecommerce.cp.core.business.saga.sagas.updateproduct.resources.UpdateProductSagaProductBackup;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public interface IUpdateProductSagaInMapper {
    static Product toProduct(ProductDTO product){
        Product tmp1 = new Product();
        tmp1.setId(product.getId());
        tmp1.setName(product.getName());
        if(product.getShortDetails() != null) {
            try {
                tmp1.setShortDetails(new ObjectMapper().writeValueAsString(product.getShortDetails()));
            } catch (JsonProcessingException e) {
                e.printStackTrace();
                tmp1.setLinks(null);
            }
        }
        if(product.getLongDetails() != null) {
            try {
                tmp1.setLongDetails(new ObjectMapper().writeValueAsString(product.getLongDetails()));
            } catch (JsonProcessingException e) {
                e.printStackTrace();
                tmp1.setLinks(null);
            }
        }
        tmp1.setCurrency(product.getCurrency());
        tmp1.setInitialPrice(product.getInitialPrice());
        tmp1.setVatPercentage(product.getVatPercentage());
        tmp1.setDiscountPercentage(product.getDiscountPercentage());
        tmp1.setShipping(product.getShipping());
        if(product.getLinks() != null) {
            try {
                tmp1.setLinks(new ObjectMapper().writeValueAsString(product.getLinks()));
            } catch (JsonProcessingException e) {
                e.printStackTrace();
                tmp1.setLinks(null);
            }
        }
        tmp1.setVisibility(product.getVisibility());
        tmp1.setSku(product.getSku());
        tmp1.setEan(product.getEan());
        tmp1.setPn(product.getPn());
        return tmp1;
    }

    static UpdateProductSagaProductBackup toUpdateProductSagaProductBackup(Product product){
        UpdateProductSagaProductBackup tmp1 = new UpdateProductSagaProductBackup();
        tmp1.setProductId(product.getId());
        tmp1.setName(product.getName());
        tmp1.setShortDetails(product.getShortDetails());
        tmp1.setLongDetails(product.getLongDetails());

        tmp1.setCurrency(product.getCurrency());
        tmp1.setInitialPrice(product.getInitialPrice());
        tmp1.setDiscountPercentage(product.getDiscountPercentage());
        tmp1.setAmountInDiscount(product.getAmountInDiscount());
        tmp1.setVatPercentage(product.getVatPercentage());
        tmp1.setAmountInVat(product.getAmountInVat());
        tmp1.setFinalPrice(product.getFinalPrice());
        tmp1.setShipping(product.getShipping());

        tmp1.setLinks(product.getLinks());
        tmp1.setVisibility(product.getVisibility());
        tmp1.setSku(product.getSku());
        tmp1.setEan(product.getEan());
        tmp1.setPn(product.getPn());
        return tmp1;
    }

    static Product toProduct(UpdateProductSagaProductBackup product){
        Product tmp1 = new Product();
        tmp1.setName(product.getName());
        tmp1.setShortDetails(product.getShortDetails());
        tmp1.setLongDetails(product.getLongDetails());

        tmp1.setCurrency(product.getCurrency());
        tmp1.setInitialPrice(product.getInitialPrice());
        tmp1.setDiscountPercentage(product.getDiscountPercentage());
        tmp1.setAmountInDiscount(product.getAmountInDiscount());
        tmp1.setVatPercentage(product.getVatPercentage());
        tmp1.setAmountInVat(product.getAmountInVat());
        tmp1.setFinalPrice(product.getFinalPrice());
        tmp1.setShipping(product.getShipping());

        tmp1.setLinks(product.getLinks());
        tmp1.setVisibility(product.getVisibility());
        tmp1.setSku(product.getSku());
        tmp1.setEan(product.getEan());
        tmp1.setPn(product.getPn());
        return tmp1;
    }
}
