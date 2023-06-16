package com.ecommerce.inventory.outbound.messaging.cqrs.updatestockstatus;

import com.ecommerce.inventory.core.business.messaging.resources.MessageStatus;
import com.ecommerce.inventory.core.business.messaging.resources.MessageType;
import com.ecommerce.inventory.core.business.resources.Product;
import com.ecommerce.inventory.core.business.resources.StockStatus;
import com.ecommerce.inventory.core.ports.out.messaging.cqrs.ICQRSUpdateStockStatus;
import com.ecommerce.inventory.outbound.messaging.messaging.producer.MessagePublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class CQRSUpdateStockStatus implements ICQRSUpdateStockStatus {

    @Value("${cqrs.0.update.stockStatus.routing.key}")
    private String routingKey;

    @Value("${cqrs.0.update.stockStatus.method.name}")
    private String method;

    @Autowired
    private MessagePublisher messagePublisher;

    @Override
    public void synchronize(String id, StockStatus stockStatus) {
        Product product = new Product();
        product.setId(id);
        product.setStockStatus(stockStatus);
        messagePublisher.publish(messagePublisher.getExchange(), routingKey, MessageType.COMMAND, MessageStatus.NO_STATUS,-1, "", method, "", product);

    }
}
