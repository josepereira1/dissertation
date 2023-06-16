package com.ecommerce.co.core.business.logic.order.query.features;

import com.ecommerce.co.core.business.exceptions.authorization.UnauthorizedException;
import com.ecommerce.co.core.business.resources.Order;
import com.ecommerce.co.core.ports.out.repository.order.IQueryOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Collection;

@Service
public class ReadOrdersByClientId {

    @Autowired
    private IQueryOrderRepository queryOrderRepository;

    public Collection<Order> readOrdersByClientId(boolean authentication, String tokenSubject, String clientId) throws UnauthorizedException {
        if(authentication && !tokenSubject.equals(clientId)) throw UnauthorizedException.builder().build();
        return queryOrderRepository.findOrderByClientId(clientId);
    }
}
