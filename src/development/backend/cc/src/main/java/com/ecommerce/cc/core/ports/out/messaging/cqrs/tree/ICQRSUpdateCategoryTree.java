package com.ecommerce.cc.core.ports.out.messaging.cqrs.tree;

import com.ecommerce.cc.core.business.resources.Version;
import com.ecommerce.cc.core.business.resources.category.Category;

public interface ICQRSUpdateCategoryTree {
    void synchronize(Version version, Category tree);
}
