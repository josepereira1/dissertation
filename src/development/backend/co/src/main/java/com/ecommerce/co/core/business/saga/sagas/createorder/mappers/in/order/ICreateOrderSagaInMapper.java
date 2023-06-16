package com.ecommerce.co.core.business.saga.sagas.createorder.mappers.in.order;

import com.ecommerce.co.core.business.resources.Address;
import com.ecommerce.co.core.business.resources.Order;
import com.ecommerce.co.core.business.resources.Product;
import com.ecommerce.co.core.business.saga.sagas.createorder.mappers.in.order.dtos.CreateOrderInputDTO;
import java.util.stream.Collectors;

public interface ICreateOrderSagaInMapper {
    static Order toOrder(CreateOrderInputDTO order){
        Order tmp1 = new Order();
        tmp1.setClientId(order.getToken());
        tmp1.setTin(order.getTin());
        tmp1.setOther(order.getOther());
        tmp1.setProducts(order.getProducts().stream().map(ICreateOrderSagaInMapper::toProduct).collect(Collectors.toList()));
        tmp1.setChargeAddress(ICreateOrderSagaInMapper.toAddress(order.getChargeAddress()));
        tmp1.setDeliveryAddress((ICreateOrderSagaInMapper.toAddress(order.getDeliveryAddress())));
        return tmp1;
    }

    static Product toProduct(Product product) {
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

    static Address toAddress(Address address){
        Address tmp1 = new Address();
        tmp1.setAddress(address.getAddress());
        tmp1.setCity(address.getCity());
        tmp1.setPostalCode(address.getPostalCode());
        tmp1.setCountry(address.getCountry());
        return tmp1;
    }
}
