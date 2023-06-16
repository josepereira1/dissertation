package com.ecommerce.cp.outbound.messaging.cqrs.product.mappers.out;

import com.ecommerce.cp.core.business.resources.product.Product;
import com.ecommerce.cp.outbound.messaging.cqrs.product.mappers.out.dtos.ProductDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public interface ICQRSProductOutMapper {
    static ProductDTO toProductDTO(Product product){
        ProductDTO tmp1 = new ProductDTO();
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
        if(product.getLongDetails() != null){
            try {
                tmp1.setLongDetails(new ObjectMapper().readValue(product.getLongDetails(), Object.class));
            } catch (JsonProcessingException e) {
                tmp1.setLongDetails(null);
                e.printStackTrace();
            }
        }
        tmp1.setCurrency(product.getCurrency());
        tmp1.setInitialPrice(product.getInitialPrice());
        tmp1.setDiscountPercentage(product.getDiscountPercentage());
        tmp1.setAmountInDiscount(product.getAmountInDiscount());
        tmp1.setVatPercentage(product.getVatPercentage());
        tmp1.setAmountInVat(product.getAmountInVat());
        tmp1.setFinalPrice(product.getFinalPrice());
        tmp1.setShipping(product.getShipping());
        tmp1.setStockStatus(product.getStockStatus());
        if(product.getLinks() != null){
            try {
                tmp1.setLinks(new ObjectMapper().readValue(product.getLinks(), Object.class));
            } catch (JsonProcessingException e) {
                tmp1.setLinks(null);
                e.printStackTrace();
            }
        }
        tmp1.setVisibility(product.getVisibility());
        tmp1.setSku(product.getSku());
        tmp1.setEan(product.getEan());
        tmp1.setPn(product.getPn());
        tmp1.setOwner(product.getOwner());
        tmp1.setCountermeasure(product.getCountermeasure());
        return tmp1;
    }
}
