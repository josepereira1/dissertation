package com.example.ecommerce.saga.core.business.framework.logic.sagadefinition.command;

import com.example.ecommerce.saga.core.business.exceptions.EcommerceBusinessLogicException;
import com.example.ecommerce.saga.core.business.framework.logic.sagadefinition.command.features.CreateSagaDefinition;
import com.example.ecommerce.saga.core.business.framework.logic.sagadefinition.command.features.DeleteSagaDefinition;
import com.example.ecommerce.saga.core.business.framework.logic.sagadefinition.command.features.UpdateSagaDefinition;
import com.example.ecommerce.saga.core.business.framework.resources.sagadefinition.SagaDefinition;
import com.example.ecommerce.saga.core.ports.in.sagadefinition.ICommandSagaDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommandSagaDefinition implements ICommandSagaDefinition {

    @Autowired
    private CreateSagaDefinition createSagaDefinition;

    @Autowired
    private UpdateSagaDefinition updateSagaDefinition;

    @Autowired
    private DeleteSagaDefinition deleteSagaDefinition;

    @Override
    public SagaDefinition createSagaDefinition(SagaDefinition sagaDefinition) throws EcommerceBusinessLogicException{
        return createSagaDefinition.createSagaDefinition(sagaDefinition);
    }

    @Override
    public SagaDefinition updateSagaDefinition(String id, SagaDefinition sagaDefinition) throws EcommerceBusinessLogicException {
        return updateSagaDefinition.updateSagaDefinition(id, sagaDefinition);
    }

    @Override
    public void deleteSagaDefinition(String id) throws EcommerceBusinessLogicException{
        deleteSagaDefinition.deleteSagaDefinition(id);
    }
}
