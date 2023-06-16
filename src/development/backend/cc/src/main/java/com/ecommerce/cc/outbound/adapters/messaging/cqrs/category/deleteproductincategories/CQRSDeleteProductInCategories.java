package com.ecommerce.cc.outbound.adapters.messaging.cqrs.category.deleteproductincategories;

import com.ecommerce.cc.core.business.messaging.resources.MessageStatus;
import com.ecommerce.cc.core.business.messaging.resources.MessageType;
import com.ecommerce.cc.core.business.resources.Version;
import com.ecommerce.cc.core.ports.out.messaging.cqrs.category.ICQRSDeleteProductInCategories;
import com.ecommerce.cc.outbound.adapters.messaging.cqrs.category.deleteproductincategories.mapper.out.dtos.ProductIdAndCategoriesDTO;
import com.ecommerce.cc.outbound.adapters.messaging.messaging.producer.MessagePublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.util.Collection;

@Service
public class CQRSDeleteProductInCategories implements ICQRSDeleteProductInCategories {

    @Value("${cqrs.0.delete.product.in.categories.routing.key}")
    private String routingKey;

    @Value("${cqrs.0.delete.product.in.categories.method.name}")
    private String method;

    @Autowired
    private MessagePublisher messagePublisher;

    @Override
    public void synchronize(Version version, String productId, Collection<Long> categoryIds) {
        ProductIdAndCategoriesDTO productIdAndCategoriesDTO = new ProductIdAndCategoriesDTO();
        productIdAndCategoriesDTO.setId(productId);
        productIdAndCategoriesDTO.setCategoryIds(categoryIds);
        messagePublisher.publish(messagePublisher.getExchange(),routingKey, MessageType.COMMAND, MessageStatus.NO_STATUS,-1, "",method, version, productIdAndCategoriesDTO);
    }
}
