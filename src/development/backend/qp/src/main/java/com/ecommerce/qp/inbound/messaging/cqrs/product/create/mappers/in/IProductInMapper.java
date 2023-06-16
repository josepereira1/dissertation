package com.ecommerce.qp.inbound.messaging.cqrs.product.create.mappers.in;

import com.ecommerce.qp.core.business.resources.product.Product;
import com.ecommerce.qp.inbound.messaging.cqrs.product.create.mappers.in.dtos.CreateProductFeatureProductDTO;

public interface IProductInMapper {
    static Product toProduct(CreateProductFeatureProductDTO product){
        Product tmp1 = new Product();
        tmp1.setId(product.getId());
        tmp1.setName(product.getName());
        tmp1.setShortDetails(product.getShortDetails());
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
        tmp1.setLongDetails(product.getLongDetails());
        tmp1.setOwner(product.getOwner());
        tmp1.setCountermeasure(product.getCountermeasure());
        return tmp1;
    }
}
