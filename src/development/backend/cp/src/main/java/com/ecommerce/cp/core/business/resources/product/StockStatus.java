package com.ecommerce.cp.core.business.resources.product;


public enum StockStatus {
    HAS_STOCK, LOW_STOCK, NO_STOCK;

    public static StockStatus getStatus(String stockStatus){
        if(stockStatus.equals(StockStatus.HAS_STOCK.toString())) return StockStatus.HAS_STOCK;
        else if(stockStatus.equals(StockStatus.LOW_STOCK.toString())) return StockStatus.LOW_STOCK;
        else if(stockStatus.equals(StockStatus.NO_STOCK.toString())) return StockStatus.NO_STOCK;
        else return null;
    }
}
