package com.ecommerce.inventory.core.business.logic.product.command.features;

import com.ecommerce.inventory.core.business.resources.StockStatus;

public class CommonCommandProduct {

    public static final Double LOW_STOCK = 50d;

    public static StockStatus getStatus(Double stock){
        if(stock == null || stock <= 0) return StockStatus.NO_STOCK;
        if(stock <= LOW_STOCK) return StockStatus.LOW_STOCK;
        else return StockStatus.HAS_STOCK;
    }
}
