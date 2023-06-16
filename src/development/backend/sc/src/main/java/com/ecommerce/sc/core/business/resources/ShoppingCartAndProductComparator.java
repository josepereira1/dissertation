package com.ecommerce.sc.core.business.resources;

import java.util.Comparator;

public class ShoppingCartAndProductComparator implements Comparator<ShoppingCartAndProduct> {

    @Override
    public int compare(ShoppingCartAndProduct o1, ShoppingCartAndProduct o2) {
        return (o1.getId().getProductId() + o1.getId().getShoppingCartId())
                .compareTo((o2.getId().getProductId() + o2.getId().getShoppingCartId()));
    }
}
