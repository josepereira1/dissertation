package com.ecommerce.cp.core.ports.out.messaging.cqrs.product;

import com.ecommerce.cp.core.business.resources.Version;
import com.ecommerce.cp.core.business.resources.product.Product;

public interface ICQRSUpdateProduct {
    void synchronize(Version version, Product product);
}
