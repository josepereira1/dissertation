package com.ecommerce.consumer.core.ports.in.consumer;

import com.ecommerce.consumer.core.business.exceptions.EcommerceBusinessLogicException;
import com.ecommerce.consumer.core.business.resources.Consumer;
import org.springframework.stereotype.Service;

@Service
public interface ICommandConsumer {
    Consumer createConsumer(Consumer consumer) throws EcommerceBusinessLogicException;
    Consumer updateConsumer(String id, Consumer consumer) throws EcommerceBusinessLogicException;
    void deleteConsumer(String id) throws EcommerceBusinessLogicException;
}
