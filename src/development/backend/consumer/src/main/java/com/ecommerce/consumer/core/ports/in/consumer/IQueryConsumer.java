package com.ecommerce.consumer.core.ports.in.consumer;

import com.ecommerce.consumer.core.business.exceptions.EcommerceBusinessLogicException;
import com.ecommerce.consumer.core.business.resources.Consumer;

public interface IQueryConsumer {
    Consumer readConsumer(String id) throws EcommerceBusinessLogicException;
}
