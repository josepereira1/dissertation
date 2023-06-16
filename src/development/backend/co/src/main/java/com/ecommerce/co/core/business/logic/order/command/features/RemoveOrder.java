package com.ecommerce.co.core.business.logic.order.command.features;

import com.ecommerce.co.core.business.exceptions.order.OrderNotExistsException;
import com.ecommerce.co.core.ports.out.repository.order.ICommandOrderRepository;
import com.ecommerce.co.core.ports.out.repository.order.IQueryOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RemoveOrder {

    @Autowired
    private ICommandOrderRepository commandOrderRepository;

    @Autowired
    private IQueryOrderRepository queryOrderRepository;

    @Transactional(rollbackFor = Exception.class)
    public void removeOrder(String id) throws OrderNotExistsException {
        if(!queryOrderRepository.existsById(id)) throw OrderNotExistsException.builder().orderId(id).build();
        commandOrderRepository.deleteById(id);
    }
}
