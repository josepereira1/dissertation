package com.example.ecommerce.saga.core.business.framework.logic.sagadefinition.command.features;

import com.example.ecommerce.saga.core.business.exceptions.sagadefinition.BadDefinitionException;
import com.example.ecommerce.saga.core.business.framework.resources.sagadefinition.SagaDefinition;

public class CommonSagaDefinition {

    public static void checkSagaDefinition(SagaDefinition sagaDefinition) throws BadDefinitionException {
        if(sagaDefinition.getRoles() == null || sagaDefinition.getRoles().size() <= 0) throw BadDefinitionException.builder().build();
        for(int i = 0; i < sagaDefinition.getParticipants().size(); i++)
            if(sagaDefinition.getParticipants().get(i) == null) throw BadDefinitionException.builder().build();
    }
}
