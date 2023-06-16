package com.ecommerce.cc.core.business.utils;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Pair<T,S> {
    private T first;
    private S second;
}
