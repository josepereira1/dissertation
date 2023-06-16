package com.ecommerce.qp.inbound.messaging.cqrs.product;

import com.ecommerce.qp.core.business.messaging.resources.IMessage;
import com.ecommerce.qp.inbound.messaging.cqrs.product.create.CQRSCreateProduct;
import com.ecommerce.qp.inbound.messaging.cqrs.product.delete.CQRSDeleteProduct;
import com.ecommerce.qp.inbound.messaging.cqrs.product.update.CQRSUpdateProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CQRSProduct {

    public static final String CQRS_0_CREATE_PRODUCT = "cqrs.0.create.product";
    public static final String CQRS_DELETE_PRODUCT = "cqrs.0.delete.product";
    public static final String CQRS_0_UPDATE_PRODUCT = "cqrs.0.update.product";


    @Autowired
    private CQRSCreateProduct cqrsCreateProduct;

    @Autowired
    private CQRSUpdateProduct cqrsUpdateProduct;

    @Autowired
    private CQRSDeleteProduct cqrsDeleteProduct;

    public void cqrsCreateProduct(IMessage message){
        cqrsCreateProduct.cqrsCreateProduct(message);
    }

    public void cqrsUpdateProduct(IMessage message){
        cqrsUpdateProduct.cqrsUpdateProduct(message);
    }

    public void cqrsDeleteProduct(IMessage message){
        cqrsDeleteProduct.cqrsDeleteProduct(message);
    }
}
