package com.ecommerce.co.core.business.logic.order.command.features;

import com.ecommerce.co.core.business.exceptions.order.CantCreateOrderWithouProductsException;
import com.ecommerce.co.core.business.resources.Order;
import com.ecommerce.co.core.business.resources.Product;
import com.ecommerce.co.core.business.resources.OrderStatus;
import com.ecommerce.co.core.ports.out.repository.order.ICommandOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class CreateOrder {

    private static final String CURRENCY_CODE = "EUR";

    @Autowired
    private ICommandOrderRepository commandOrderRepository;

    @Transactional(rollbackFor = Exception.class)
    public Order createOrder(Order order) throws CantCreateOrderWithouProductsException {
        order.setInitialDate(LocalDateTime.now());
        order.setStatus(OrderStatus.PENDING_PAYMENT);
        if(order.getProducts() != null && order.getProducts().size() > 0) calculateValues(order);
        else throw CantCreateOrderWithouProductsException.builder().build();
        order = commandOrderRepository.save(order);
        return order;
    }

    private void calculateValues(Order order){
        double subtotal = 0, discount = 0, shipping = 0, vat = 0, total = 0;
        for(Product product : order.getProducts()){
            subtotal += product.getInitialPrice()*product.getQuantity();
            discount += product.getAmountInDiscount()*product.getQuantity();
            shipping += product.getShipping()*product.getQuantity();
            vat += product.getAmountInVat()*product.getQuantity();
            total += product.getFinalPrice()*product.getQuantity();
        }
        order.setCurrency(CURRENCY_CODE);
        order.setSubtotal(subtotal);
        order.setDiscount(discount);
        order.setShipping(shipping);
        order.setVat(vat);
        order.setTotal(total);
    }
}
