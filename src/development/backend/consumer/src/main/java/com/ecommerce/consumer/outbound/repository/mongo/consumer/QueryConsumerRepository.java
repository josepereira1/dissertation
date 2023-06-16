package com.ecommerce.consumer.outbound.repository.mongo.consumer;

import com.ecommerce.consumer.core.business.resources.Consumer;
import com.ecommerce.consumer.core.ports.out.repository.consumer.IQueryConsumerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class QueryConsumerRepository implements IQueryConsumerRepository {

    @Autowired
    private ConsumerDAO consumerDAO;

    @Override
    public Optional<Consumer> findById(String id) {
        return consumerDAO.findById(id);
    }

    @Override
    public boolean existsById(String id) {
        return consumerDAO.existsById(id);
    }
}
