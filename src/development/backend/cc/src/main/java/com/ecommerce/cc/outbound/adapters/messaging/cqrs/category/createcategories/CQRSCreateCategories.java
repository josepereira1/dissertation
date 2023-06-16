package com.ecommerce.cc.outbound.adapters.messaging.cqrs.category.createcategories;

import com.ecommerce.cc.core.business.messaging.resources.MessageStatus;
import com.ecommerce.cc.core.business.messaging.resources.MessageType;
import com.ecommerce.cc.core.business.resources.Version;
import com.ecommerce.cc.core.business.resources.category.Category;
import com.ecommerce.cc.core.ports.out.messaging.cqrs.category.ICQRSCreateCategories;
import com.ecommerce.cc.outbound.adapters.messaging.messaging.producer.MessagePublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class CQRSCreateCategories implements ICQRSCreateCategories {

    @Value("${cqrs.0.create.categories.routing.key}")
    private String routingKey;

    @Value("${cqrs.0.create.categories.method.name}")
    private String method;

    @Autowired
    private MessagePublisher messagePublisher;

    @Override
    public void synchronize(Version version, Category category) {
        messagePublisher.publish(messagePublisher.getExchange(), routingKey, MessageType.COMMAND, MessageStatus.NO_STATUS,-1,"",method, version,  category);
    }
}
