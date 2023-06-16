package com.ecommerce.cc.outbound.adapters.messaging.cqrs.tree.createcategorytree;

import com.ecommerce.cc.core.business.messaging.resources.MessageStatus;
import com.ecommerce.cc.core.business.messaging.resources.MessageType;
import com.ecommerce.cc.core.business.resources.Version;
import com.ecommerce.cc.core.business.resources.category.Category;
import com.ecommerce.cc.core.ports.out.messaging.cqrs.tree.ICQRSUpdateCategoryTree;
import com.ecommerce.cc.outbound.adapters.messaging.messaging.producer.MessagePublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class CQRSUpdateCategoryTree implements ICQRSUpdateCategoryTree {
    @Value("${cqrs.0.update.category.tree.routing.key}")
    private String routingKey;

    @Value("${cqrs.0.update.category.tree.method.name}")
    private String method;

    @Autowired
    private MessagePublisher messagePublisher;

    @Override
    public void synchronize(Version version, Category tree) {
        messagePublisher.publish(messagePublisher.getExchange(), routingKey, MessageType.COMMAND, MessageStatus.NO_STATUS,-1,"",method, version, tree);
    }
}
