package com.ecommerce.qp.inbound.rest.product.mappers.out;

import com.ecommerce.qp.core.business.resources.product.Product;
import com.ecommerce.qp.inbound.rest.product.mappers.out.dtos.ReadProductDTO;

public interface IProductOutMapper {
    static ReadProductDTO toReadProductFeatureProductDTO(Product product){
        ReadProductDTO tmp1 = new ReadProductDTO();
        tmp1.setId(product.getId());
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
        tmp1.setStockStatus(product.getStockStatus());
        tmp1.setLinks(product.getLinks());
        tmp1.setVisibility(product.getVisibility());
        tmp1.setSku(product.getSku());
        tmp1.setEan(product.getEan());
        tmp1.setPn(product.getPn());
        return tmp1;
    }
}
