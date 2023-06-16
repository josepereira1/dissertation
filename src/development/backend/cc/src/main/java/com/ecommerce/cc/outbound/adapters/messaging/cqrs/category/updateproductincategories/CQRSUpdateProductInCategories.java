package com.ecommerce.cc.outbound.adapters.messaging.cqrs.category.updateproductincategories;

import com.ecommerce.cc.core.business.exceptions.EcommerceBusinessLogicException;
import com.ecommerce.cc.core.business.messaging.resources.MessageStatus;
import com.ecommerce.cc.core.business.messaging.resources.MessageType;
import com.ecommerce.cc.core.business.resources.Version;
import com.ecommerce.cc.core.business.resources.product.Product;
import com.ecommerce.cc.core.ports.out.messaging.cqrs.category.ICQRSUpdateProductInCategories;
import com.ecommerce.cc.outbound.adapters.messaging.cqrs.category.updateproductincategories.mapper.out.IProductAndAddCategoriesAndRemoveCategoriesDTOOutMapper;
import com.ecommerce.cc.outbound.adapters.messaging.messaging.producer.MessagePublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.util.Collection;

@Service
public class CQRSUpdateProductInCategories implements ICQRSUpdateProductInCategories {

    @Value("${cqrs.0.update.product.in.categories.routing.key}")
    private String routingKey;

    @Value("${cqrs.0.update.product.in.categories.method.name}")
    private String method;

    @Autowired
    private MessagePublisher messagePublisher;

    @Override
    public void synchronize(Version version, Product product, Collection<Long> currentProductCategories, Collection<Long> addCategoryIds, Collection<Long> removeCategoryIds) throws EcommerceBusinessLogicException {
        messagePublisher.publish(messagePublisher.getExchange(),routingKey, MessageType.COMMAND, MessageStatus.NO_STATUS,-1,"",method, version, IProductAndAddCategoriesAndRemoveCategoriesDTOOutMapper.toProductAndAddCategoriesAndRemoveCategoriesDTO(product,currentProductCategories, addCategoryIds, removeCategoryIds));
    }
}
