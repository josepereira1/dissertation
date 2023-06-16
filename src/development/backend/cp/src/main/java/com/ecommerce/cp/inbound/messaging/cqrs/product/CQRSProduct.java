package com.ecommerce.cp.inbound.messaging.cqrs.product;

import com.ecommerce.cp.core.business.messaging.resources.IMessage;
import com.ecommerce.cp.inbound.messaging.cqrs.product.updatestockstatus.CQRSUpdateStockStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CQRSProduct {

    public static final String CQRS_UPDATE_STOCK_STATUS = "cqrs.0.update.stockStatus";

    @Autowired
    private CQRSUpdateStockStatus cqrsUpdateStockStatus;

    public void cqrsUpdateStockStatus(IMessage message){
        cqrsUpdateStockStatus.cqrsUpdateStockStatus(message);
    }
}
