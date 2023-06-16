package com.ecommerce.consumer.outbound.repository.mongo.consumer;

import com.ecommerce.consumer.core.business.resources.Consumer;
import com.ecommerce.consumer.core.ports.out.repository.consumer.ICommandConsumerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommandConsumerRepository implements ICommandConsumerRepository {

    @Autowired
    private ConsumerDAO consumerDAO;

    @Override
    public Consumer save(Consumer consumer) {
        return consumerDAO.save(consumer);
    }
}
