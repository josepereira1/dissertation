package com.ecommerce.cp.core.business.saga.sagas.createproduct.mappers.in;

import com.ecommerce.cp.core.business.resources.product.Product;
import com.ecommerce.cp.core.business.saga.sagas.createproduct.mappers.in.dtos.ProductDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public interface ICreateProductSagaInMapper {

    static Product toProduct(ProductDTO product){
        Product tmp1 = new Product();
        tmp1.setId(product.getId());
        tmp1.setName(product.getName());
        try {
            tmp1.setShortDetails(new ObjectMapper().writeValueAsString(product.getShortDetails()));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            tmp1.setLinks(null);
        }
        try {
            tmp1.setLongDetails(new ObjectMapper().writeValueAsString(product.getLongDetails()));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            tmp1.setLinks(null);
        }
        tmp1.setCurrency(product.getCurrency());
        tmp1.setInitialPrice(product.getInitialPrice());
        tmp1.setVatPercentage(product.getVatPercentage());
        tmp1.setDiscountPercentage(product.getDiscountPercentage());
        tmp1.setShipping(product.getShipping());
        try {
            tmp1.setLinks(new ObjectMapper().writeValueAsString(product.getLinks()));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            tmp1.setLinks(null);
        }
        tmp1.setVisibility(product.getVisibility());
        tmp1.setSku(product.getSku());
        tmp1.setEan(product.getEan());
        tmp1.setPn(product.getPn());
        return tmp1;
    }
}
