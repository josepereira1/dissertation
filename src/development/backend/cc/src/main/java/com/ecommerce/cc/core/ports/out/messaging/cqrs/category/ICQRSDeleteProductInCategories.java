package com.ecommerce.cc.core.ports.out.messaging.cqrs.category;

import com.ecommerce.cc.core.business.resources.Version;
import java.util.Collection;

public interface ICQRSDeleteProductInCategories {
    void synchronize(Version version, String ProductId, Collection<Long> categoryIds);
}
