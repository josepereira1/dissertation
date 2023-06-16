package com.ecommerce.consumer.core.business.logic.client.query;

import com.ecommerce.consumer.core.business.exceptions.EcommerceBusinessLogicException;
import com.ecommerce.consumer.core.business.logic.client.query.features.ReadConsumer;
import com.ecommerce.consumer.core.business.resources.Consumer;
import com.ecommerce.consumer.core.ports.in.consumer.IQueryConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QueryConsumer implements IQueryConsumer {

    @Autowired
    private ReadConsumer readConsumer;

    @Override
    public Consumer readConsumer(String id) throws EcommerceBusinessLogicException {
        return readConsumer.readConsumer(id);
    }
}
