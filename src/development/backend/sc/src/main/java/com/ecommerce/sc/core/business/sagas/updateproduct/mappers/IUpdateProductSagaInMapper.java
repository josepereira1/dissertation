package com.ecommerce.sc.core.business.sagas.updateproduct.mappers;

import com.ecommerce.sc.core.business.resources.Product;
import com.ecommerce.sc.core.business.sagas.updateproduct.mappers.dtos.ProductDTO;
import com.ecommerce.sc.core.business.sagas.updateproduct.resources.UpdateProductSagaProductBackup;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public interface IUpdateProductSagaInMapper {

    static Product toProduct(ProductDTO product){
        Product tmp1 = new Product();
        tmp1.setId(product.getId());
        tmp1.setName(product.getName());
        tmp1.setCurrency(product.getCurrency());
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
        tmp1.setSku(product.getSku());
        tmp1.setEan(product.getEan());
        tmp1.setPn(product.getPn());
        return tmp1;
    }

    static Product toProduct(UpdateProductSagaProductBackup product){
        Product tmp1 = new Product();
        tmp1.setId(product.getProductId());
        tmp1.setName(product.getName());
        tmp1.setCurrency(product.getCurrency());
        tmp1.setFinalPrice(product.getFinalPrice());
        tmp1.setLinks(product.getLinks());
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
        tmp1.setCurrency(product.getCurrency());
        tmp1.setFinalPrice(product.getFinalPrice());
        tmp1.setLinks(product.getLinks());
        tmp1.setVisibility(product.getVisibility());
        tmp1.setSku(product.getSku());
        tmp1.setEan(product.getEan());
        tmp1.setPn(product.getPn());
        return tmp1;
    }
}
