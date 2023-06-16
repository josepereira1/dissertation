package com.ecommerce.inventory.core.ports.out.messaging.cqrs;

import com.ecommerce.inventory.core.business.resources.StockStatus;

public interface ICQRSUpdateStockStatus {
    void synchronize(String id, StockStatus stockStatus);
}
