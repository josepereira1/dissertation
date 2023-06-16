package com.example.ecommerce.saga.core.ports.out.saga;

import com.example.ecommerce.saga.core.business.framework.resources.saga.Saga;

public interface ICommandSagaRepository {
    Saga save(Saga saga);
}
