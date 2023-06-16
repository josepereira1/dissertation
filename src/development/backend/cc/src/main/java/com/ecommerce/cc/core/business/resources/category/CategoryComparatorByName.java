package com.ecommerce.cc.core.business.resources.category;

import java.util.Comparator;

public class CategoryComparatorByName implements Comparator {

    @Override
    public int compare(Object o1, Object o2) {
        if(o1 == null && o2 == null) return 0;
        return ((Category) o1).getName().compareTo(((Category) o2).getName());
    }
}
