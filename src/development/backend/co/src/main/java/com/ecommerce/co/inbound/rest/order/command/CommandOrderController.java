package com.ecommerce.co.inbound.rest.order.command;

import com.ecommerce.co.core.business.exceptions.EcommerceBusinessLogicException;
import com.ecommerce.co.core.business.resources.OrderStatus;
import com.ecommerce.co.core.ports.in.order.ICommandOrder;
import com.ecommerce.co.inbound.rest.order.mappers.out.IOrderOutMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CommandOrderController implements ICommandOrderController{

    @Autowired
    private ICommandOrder commandOrder;

    @Override
    public ResponseEntity updateOrderStatus(String token, String id, OrderStatus status) throws EcommerceBusinessLogicException {
        return ResponseEntity.ok(IOrderOutMapper.toOrderDTO(commandOrder.updateOrderStatus(id, status)));
    }

    @Override
    public ResponseEntity deleteOrder(String token, String id) throws EcommerceBusinessLogicException {
        commandOrder.removeOrder(id);
        return new ResponseEntity("", HttpStatus.NO_CONTENT);
    }
}
