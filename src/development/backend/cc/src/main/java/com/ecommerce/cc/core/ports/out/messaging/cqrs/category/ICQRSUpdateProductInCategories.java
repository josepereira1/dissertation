package com.ecommerce.cc.core.ports.out.messaging.cqrs.category;

import com.ecommerce.cc.core.business.exceptions.EcommerceBusinessLogicException;
import com.ecommerce.cc.core.business.resources.Version;
import com.ecommerce.cc.core.business.resources.product.Product;
import java.util.Collection;

public interface ICQRSUpdateProductInCategories {
    void synchronize(Version version, Product product, Collection<Long> currentProductCategories, Collection<Long> addCategoryIds, Collection<Long> removeCategoryIds) throws EcommerceBusinessLogicException;
}
