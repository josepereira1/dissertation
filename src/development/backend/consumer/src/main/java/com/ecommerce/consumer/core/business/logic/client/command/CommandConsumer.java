package com.ecommerce.consumer.core.business.logic.client.command;

import com.ecommerce.consumer.core.business.exceptions.EcommerceBusinessLogicException;
import com.ecommerce.consumer.core.business.logic.client.command.features.CreateConsumer;
import com.ecommerce.consumer.core.business.logic.client.command.features.UpdateConsumer;
import com.ecommerce.consumer.core.business.resources.Consumer;
import com.ecommerce.consumer.core.ports.in.consumer.ICommandConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommandConsumer implements ICommandConsumer {

    @Autowired
    private CreateConsumer createConsumer;

    @Autowired
    private UpdateConsumer updateConsumer;

    @Override
    public Consumer createConsumer(Consumer consumer) throws EcommerceBusinessLogicException {
        return createConsumer.createConsumer(consumer);
    }

    @Override
    public Consumer updateConsumer(String id, Consumer consumer) throws EcommerceBusinessLogicException{
        return updateConsumer.updateConsumer(id, consumer);
    }

    @Override
    public void deleteConsumer(String id) throws EcommerceBusinessLogicException{

    }
}
