package com.ecommerce.cc.core.ports.out.messaging.cqrs.category;

import com.ecommerce.cc.core.business.exceptions.EcommerceBusinessLogicException;
import com.ecommerce.cc.core.business.resources.Version;
import com.ecommerce.cc.core.business.resources.product.Product;
import java.util.Collection;

public interface ICQRSCreateProductInCategories {
    void synchronize(Version version, Product product, Collection<Long> categoryIds) throws EcommerceBusinessLogicException;
}
