package com.example.ecommerce.saga.outbound.mongo.sagadefinition;

import com.example.ecommerce.saga.core.business.framework.resources.sagadefinition.SagaDefinition;
import com.example.ecommerce.saga.core.ports.out.sagadefinition.ICommandSagaDefinitionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommandSagaDefinitionRepository implements ICommandSagaDefinitionRepository {

    @Autowired
    private SagaDefinitionDAO sagaDefinitionDAO;

    @Override
    public SagaDefinition save(SagaDefinition sagaDefinition) {
        return sagaDefinitionDAO.save(sagaDefinition);
    }

    @Override
    public void deleteById(String id) {
        sagaDefinitionDAO.deleteById(id);
    }
}
