package com.ecommerce.cc.outbound.adapters.postgres.category.mappers.out.projections;

import com.ecommerce.cc.core.business.resources.product.StockStatus;

public interface ProductIdAndNameAndPriceAndDiscountAndStockStatus {
    String getId();
    String getName();
    String getShortDetails();
    String getCurrency();
    Double getDiscountPercentage();
    Double getAmountInDiscount();
    Double getFinalPrice();
    StockStatus getStockStatus();
    String getPn();
}
