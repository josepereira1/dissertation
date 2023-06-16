package com.ecommerce.cc.outbound.adapters.messaging.cqrs.category.createproductincategories;

import com.ecommerce.cc.core.business.exceptions.EcommerceBusinessLogicException;
import com.ecommerce.cc.core.business.messaging.resources.MessageStatus;
import com.ecommerce.cc.core.business.messaging.resources.MessageType;
import com.ecommerce.cc.core.business.resources.Version;
import com.ecommerce.cc.core.business.resources.product.Product;
import com.ecommerce.cc.core.ports.out.messaging.cqrs.category.ICQRSCreateProductInCategories;
import com.ecommerce.cc.outbound.adapters.messaging.cqrs.category.createproductincategories.mapper.out.IProductAndCategoriesDTOOutMapper;
import com.ecommerce.cc.outbound.adapters.messaging.messaging.producer.MessagePublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.util.Collection;

@Service
public class CQRSCreateProductInCategories implements ICQRSCreateProductInCategories {

    @Value("${cqrs.0.create.product.in.categories.routing.key}")
    private String routingKey;

    @Value("${cqrs.0.create.product.in.categories.method.name}")
    private String method;

    @Autowired
    private MessagePublisher messagePublisher;

    @Override
    public void synchronize(Version version, Product product, Collection<Long> categoryIds) throws EcommerceBusinessLogicException {
        messagePublisher.publish(messagePublisher.getExchange(), routingKey, MessageType.COMMAND, MessageStatus.NO_STATUS,-1,"",method, version, IProductAndCategoriesDTOOutMapper.toProductAndCategoriesDTO(product,categoryIds));
    }
}
