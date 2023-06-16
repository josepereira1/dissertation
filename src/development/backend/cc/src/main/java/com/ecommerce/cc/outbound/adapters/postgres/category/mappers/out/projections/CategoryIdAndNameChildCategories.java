package com.ecommerce.cc.outbound.adapters.postgres.category.mappers.out.projections;

import java.util.Set;

public interface CategoryIdAndNameChildCategories {
    Long getId();
    String getName();
    Set<CategoryIdAndNameChildCategories> getChildCategories();
}
