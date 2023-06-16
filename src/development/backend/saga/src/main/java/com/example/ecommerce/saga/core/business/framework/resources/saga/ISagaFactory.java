package com.example.ecommerce.saga.core.business.framework.resources.saga;

import org.springframework.stereotype.Service;

@Service
public interface ISagaFactory {
    Saga factory(String name, Integer size);
}
