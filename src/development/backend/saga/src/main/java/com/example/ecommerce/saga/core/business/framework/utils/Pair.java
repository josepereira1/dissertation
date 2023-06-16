package com.example.ecommerce.saga.core.business.framework.utils;

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
