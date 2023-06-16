package com.ecommerce.sc.core.business.sagas.createproduct.mappers;

import com.ecommerce.sc.core.business.resources.Product;
import com.ecommerce.sc.core.business.sagas.createproduct.mappers.dtos.ProductDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public interface ICreateProductSagaInMapper {

    static Product toProduct(ProductDTO product){
        Product tmp1 = new Product();
        tmp1.setId(product.getId());
        tmp1.setName(product.getName());
        tmp1.setCurrency(product.getCurrency());
        tmp1.setFinalPrice(product.getFinalPrice());
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
