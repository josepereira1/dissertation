package com.ecommerce.cc.outbound.adapters.messaging.cqrs.category.deletecategories;

import com.ecommerce.cc.core.business.messaging.resources.MessageStatus;
import com.ecommerce.cc.core.business.messaging.resources.MessageType;
import com.ecommerce.cc.core.business.resources.Version;
import com.ecommerce.cc.core.ports.out.messaging.cqrs.category.ICQRSDeleteCategories;
import com.ecommerce.cc.outbound.adapters.messaging.messaging.producer.MessagePublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.util.Collection;

@Service
public class CQRSDeleteCategories implements ICQRSDeleteCategories {

    @Value("${cqrs.0.delete.categories.routing.key}")
    private String routingKey;

    @Value("${cqrs.0.delete.categories.method.name}")
    private String method;

    @Autowired
    private MessagePublisher messagePublisher;

    @Override
    public void synchronize(Version version, Collection<String> ids) {
        messagePublisher.publish(messagePublisher.getExchange(),routingKey, MessageType.COMMAND, MessageStatus.NO_STATUS,-1,"",method, version, ids);
    }
}
