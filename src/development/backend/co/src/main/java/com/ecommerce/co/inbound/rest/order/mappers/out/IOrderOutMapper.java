package com.ecommerce.co.inbound.rest.order.mappers.out;

import com.ecommerce.co.core.business.resources.Address;
import com.ecommerce.co.core.business.resources.Order;
import com.ecommerce.co.core.business.resources.Product;
import com.ecommerce.co.inbound.rest.order.mappers.out.dtos.*;
import java.util.stream.Collectors;

public interface IOrderOutMapper {
    static OrderDTO toOrderDTO(Order order){
        OrderDTO tmp1 = new OrderDTO();
        tmp1.setId(order.getId());
        tmp1.setClientId(order.getClientId());
        tmp1.setTin(order.getTin());
        tmp1.setStatus(order.getStatus());
        tmp1.setStatusCode(order.getStatusCode());
        tmp1.setStatusMessage(order.getStatusMessage());
        tmp1.setInitialDate(order.getInitialDate());
        tmp1.setEndDate(order.getEndDate());
        tmp1.setCurrency(order.getCurrency());
        tmp1.setSubtotal(order.getSubtotal());
        tmp1.setDiscount(order.getDiscount());
        tmp1.setShipping(order.getShipping());
        tmp1.setVat(order.getVat());
        tmp1.setTotal(order.getTotal());
        tmp1.setTrackingNumber(order.getTrackingNumber());
        tmp1.setProducts(order.getProducts().stream().map(IOrderOutMapper::toProductDTO).collect(Collectors.toList()));
        tmp1.setChargeAddress(IOrderOutMapper.toAddressDTO(order.getChargeAddress()));
        tmp1.setDeliveryAddress(IOrderOutMapper.toAddressDTO(order.getDeliveryAddress()));
        tmp1.setOther(order.getOther());
        return tmp1;
    }

    static ProductDTO toProductDTO(Product product){
        ProductDTO tmp1 = new ProductDTO();
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

    static AddressDTO toAddressDTO(Address address){
        AddressDTO tmp1 = new AddressDTO();
        tmp1.setAddress(address.getAddress());
        tmp1.setCity(address.getCity());
        tmp1.setPostalCode(address.getPostalCode());
        tmp1.setCountry(address.getCountry());
        return tmp1;
    }

    static OrderIdAndClientIdAndStatusAndTotalAndInitialDateDTO toOrderIdAndStatusAndValuesAndInitialDateDTO(Order order){
        OrderIdAndClientIdAndStatusAndTotalAndInitialDateDTO tmp1 = new OrderIdAndClientIdAndStatusAndTotalAndInitialDateDTO();
        tmp1.setId(order.getId());
        tmp1.setClientId(order.getClientId());
        tmp1.setStatus(order.getStatus());
        tmp1.setTotal(order.getTotal());
        tmp1.setCurrency(order.getCurrency());
        tmp1.setInitialDate(order.getInitialDate());
        return tmp1;
    }
}
