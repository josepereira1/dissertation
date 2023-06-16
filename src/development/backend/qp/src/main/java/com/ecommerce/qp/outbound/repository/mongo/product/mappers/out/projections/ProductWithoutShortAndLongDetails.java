package com.ecommerce.qp.outbound.repository.mongo.product.mappers.out.projections;

public interface ProductWithoutShortAndLongDetails {
    String getId();
    String getName();
    String getCurrency();
    Double getDiscountPercentage();
    Double getAmountInDiscount();
    Double getVatPercentage();
    Double getAmountInVat();
    Double getFinalPrice();
    Double getShipping();
    String getSku();
    String getEan();
    String getPn();
}
