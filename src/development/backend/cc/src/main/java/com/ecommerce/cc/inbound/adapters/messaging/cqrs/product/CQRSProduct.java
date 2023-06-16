package com.ecommerce.cc.inbound.adapters.messaging.cqrs.product;

import com.ecommerce.cc.core.business.messaging.resources.IMessage;
import com.ecommerce.cc.inbound.adapters.messaging.cqrs.product.updatestockstatus.CQRSUpdateStockStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CQRSProduct {

    public static final String CQRS_UPDATE_STOCK_STATUS_METHOD_NAME = "cqrs.0.update.stockStatus";

    @Autowired
    private CQRSUpdateStockStatus cqrsUpdateStockStatus;

    public void cqrsUpdateStockStatus(IMessage message){
        cqrsUpdateStockStatus.cqrsUpdateStockStatus(message);
    }
}
