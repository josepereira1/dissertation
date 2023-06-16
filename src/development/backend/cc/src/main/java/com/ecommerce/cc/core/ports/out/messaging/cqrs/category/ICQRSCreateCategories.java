package com.ecommerce.cc.core.ports.out.messaging.cqrs.category;

import com.ecommerce.cc.core.business.resources.Version;
import com.ecommerce.cc.core.business.resources.category.Category;

public interface ICQRSCreateCategories {
    void synchronize(Version version, Category category);
}
