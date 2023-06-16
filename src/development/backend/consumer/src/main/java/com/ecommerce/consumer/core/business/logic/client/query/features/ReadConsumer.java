package com.ecommerce.consumer.core.business.logic.client.query.features;

import com.ecommerce.consumer.core.business.exceptions.consumer.ConsumerNotExistsException;
import com.ecommerce.consumer.core.business.resources.Consumer;
import com.ecommerce.consumer.core.ports.out.repository.consumer.IQueryConsumerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class ReadConsumer {

    @Autowired
    private IQueryConsumerRepository queryClientRepository;

    public Consumer readConsumer(String id) throws ConsumerNotExistsException {
        Optional<Consumer> optional = queryClientRepository.findById(id);
        if(!optional.isPresent()) throw ConsumerNotExistsException.builder().id(id).build();
        else return optional.get();
    }
}
