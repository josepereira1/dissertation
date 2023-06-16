package com.ecommerce.cc.outbound.adapters.messaging.cqrs.category.updatecategoryname;

import com.ecommerce.cc.core.business.exceptions.EcommerceBusinessLogicException;
import com.ecommerce.cc.core.business.messaging.resources.MessageStatus;
import com.ecommerce.cc.core.business.messaging.resources.MessageType;
import com.ecommerce.cc.core.business.resources.Version;
import com.ecommerce.cc.core.business.resources.category.Category;
import com.ecommerce.cc.core.ports.in.version.ICommandVersion;
import com.ecommerce.cc.core.ports.out.messaging.cqrs.category.ICQRSUpdateCategoryName;
import com.ecommerce.cc.outbound.adapters.messaging.messaging.producer.MessagePublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class CQRSUpdateCategoryName implements ICQRSUpdateCategoryName {

    @Value("${cqrs.0.update.category.name.routing.key}")
    private String routingKey;

    @Value("${cqrs.0.update.category.name.method.name}")
    private String method;

    @Autowired
    private MessagePublisher messagePublisher;

    @Autowired
    private ICommandVersion commandVersion;

    @Override
    public void synchronize(Version version, Long id, String name) throws EcommerceBusinessLogicException {
        Category category = new Category();
        category.setId(id);
        category.setName(name);
        messagePublisher.publish(messagePublisher.getExchange(),routingKey, MessageType.COMMAND, MessageStatus.NO_STATUS,-1,"", method, version, category);
    }
}
