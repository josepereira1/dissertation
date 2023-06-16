package com.ecommerce.qp.outbound.repository.mongo.product.mappers.out.projections;

import com.ecommerce.qp.core.business.resources.product.Product;

public interface IProductOutMapper {
    static Product toProduct(ProductWithoutShortAndLongDetails product){
        Product tmp1 = new Product();
        tmp1.setId(product.getId());
        tmp1.setName(product.getName());

        tmp1.setCurrency(product.getCurrency());
        tmp1.setDiscountPercentage(product.getDiscountPercentage());
        tmp1.setAmountInDiscount(product.getAmountInDiscount());
        tmp1.setVatPercentage(product.getVatPercentage());
        tmp1.setAmountInVat(product.getAmountInVat());
        tmp1.setFinalPrice(product.getFinalPrice());
        tmp1.setShipping(product.getShipping());

        tmp1.setSku(product.getSku());
        tmp1.setEan(product.getEan());
        tmp1.setPn(product.getPn());
        return tmp1;
    }
}
