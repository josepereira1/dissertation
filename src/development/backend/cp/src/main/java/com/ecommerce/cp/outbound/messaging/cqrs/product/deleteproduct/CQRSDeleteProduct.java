package com.ecommerce.cp.outbound.messaging.cqrs.product.deleteproduct;

import com.ecommerce.cp.core.business.messaging.resources.MessageStatus;
import com.ecommerce.cp.core.business.messaging.resources.MessageType;
import com.ecommerce.cp.core.business.resources.CounterMeasure;
import com.ecommerce.cp.core.business.resources.Version;
import com.ecommerce.cp.core.business.resources.product.Product;
import com.ecommerce.cp.core.ports.out.messaging.cqrs.product.ICQRSDeleteProduct;
import com.ecommerce.cp.outbound.messaging.messaging.producer.MessagePublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class CQRSDeleteProduct implements ICQRSDeleteProduct {

    @Value("${cqrs.0.delete.product.routing.key}")
    private String routingKey;

    @Value("${cqrs.0.delete.product.method.name}")
    private String method;

    @Autowired
    private MessagePublisher messagePublisher;

    @Override
    public void synchronize(Version version, Product product) {
        messagePublisher.publish(messagePublisher.getExchange(), routingKey, MessageType.COMMAND, MessageStatus.NO_STATUS,-1, "", method, version, product);
    }
}
