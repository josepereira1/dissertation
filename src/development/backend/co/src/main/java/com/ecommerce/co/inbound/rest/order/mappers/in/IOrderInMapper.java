package com.ecommerce.co.inbound.rest.order.mappers.in;

import com.ecommerce.co.core.business.resources.Address;
import com.ecommerce.co.core.business.resources.Order;
import com.ecommerce.co.core.business.resources.OrderStatus;
import com.ecommerce.co.core.business.resources.Product;
import com.ecommerce.co.inbound.rest.order.mappers.in.dtos.CreateOrderAddressDTO;
import com.ecommerce.co.inbound.rest.order.mappers.in.dtos.CreateOrderOrderDTO;
import com.ecommerce.co.inbound.rest.order.mappers.in.dtos.CreateOrderProductDTO;
import com.ecommerce.co.inbound.rest.order.mappers.in.dtos.OrderFilterParameterDTO;

import java.util.stream.Collectors;

public interface IOrderInMapper {

    static Order toOrder(CreateOrderOrderDTO order){
        Order tmp1 = new Order();
        tmp1.setClientId(order.getClientId());
        tmp1.setTin(order.getTin());
        tmp1.setOther(order.getOther());
        tmp1.setProducts(order.getProducts().stream().map(IOrderInMapper::toProduct).collect(Collectors.toList()));
        tmp1.setChargeAddress(IOrderInMapper.toAddress(order.getChargeAddress()));
        tmp1.setDeliveryAddress((IOrderInMapper.toAddress(order.getDeliveryAddress())));
        tmp1.setCountermeasure(order.getCountermeasure());
        return tmp1;
    }

    static Product toProduct(CreateOrderProductDTO product) {
        Product tmp1 = new Product();
        tmp1.setId(product.getId());
        tmp1.setName(product.getName());
        tmp1.setQuantity(product.getQuantity());

        tmp1.setCurrency(product.getCurrency());
        tmp1.setInitialPrice(product.getInitialPrice());
        tmp1.setDiscountPercentage(product.getDiscountPercentage());
        tmp1.setAmountInDiscount(product.getAmountInDiscount());
        tmp1.setVatPercentage(product.getVatPercentage());
        tmp1.setAmountInVat(product.getAmountInVat());
        tmp1.setFinalPrice(product.getFinalPrice());
        tmp1.setShipping(product.getShipping());

        tmp1.setSku(product.getSku());
        tmp1.setEan(product.getEan());
        tmp1.setPn(product.getPn());
        return tmp1;
    }

    static Address toAddress(CreateOrderAddressDTO address){
        Address tmp1 = new Address();
        tmp1.setAddress(address.getAddress());
        tmp1.setCity(address.getCity());
        tmp1.setPostalCode(address.getPostalCode());
        tmp1.setCountry(address.getCountry());
        return tmp1;
    }

    static OrderStatus toOrderStatus(OrderFilterParameterDTO filterParameterDTO){
        //  ALL, PENDING_PAYMENT, FAILED, PROCESSING, COMPLETED, CANCELED
        switch (filterParameterDTO){
            case PENDING_PAYMENT:
                return OrderStatus.PENDING_PAYMENT;
            case FAILED:
                return OrderStatus.FAILED;
            case PROCESSING:
                return OrderStatus.PROCESSING;
            case COMPLETED:
                return OrderStatus.COMPLETED;
            case CANCELED:
                return OrderStatus.CANCELED;
            default:
                return null;
        }

    }
}
