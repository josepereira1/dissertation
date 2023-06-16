package com.ecommerce.cc.core.ports.out.messaging.cqrs.category;

import com.ecommerce.cc.core.business.exceptions.EcommerceBusinessLogicException;
import com.ecommerce.cc.core.business.resources.Version;

public interface ICQRSUpdateCategoryName {
    void synchronize(Version version, Long id, String name) throws EcommerceBusinessLogicException;
}
