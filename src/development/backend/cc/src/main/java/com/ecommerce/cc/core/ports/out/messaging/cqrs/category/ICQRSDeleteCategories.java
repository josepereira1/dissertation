package com.ecommerce.cc.core.ports.out.messaging.cqrs.category;

import com.ecommerce.cc.core.business.resources.Version;
import java.util.Collection;

public interface ICQRSDeleteCategories {
    //  TODO aqui tem que ser Long
    void synchronize(Version version, Collection<String> ids);
}
