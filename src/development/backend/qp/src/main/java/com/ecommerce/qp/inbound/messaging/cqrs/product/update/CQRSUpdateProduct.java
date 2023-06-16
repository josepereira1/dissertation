package com.ecommerce.qp.inbound.messaging.cqrs.product.update;

import com.ecommerce.qp.core.business.exceptions.product.ProductNotExistException;
import com.ecommerce.qp.core.business.logs.ILogs;
import com.ecommerce.qp.core.business.messaging.resources.IMessage;
import com.ecommerce.qp.core.business.resources.CounterMeasure;
import com.ecommerce.qp.core.business.resources.Version;
import com.ecommerce.qp.core.business.resources.product.Product;
import com.ecommerce.qp.core.ports.in.product.ICommandProduct;
import com.ecommerce.qp.inbound.messaging.cqrs.product.update.mappers.in.IProductInMapper;
import com.ecommerce.qp.inbound.messaging.cqrs.product.update.mappers.in.dtos.UpdateProductFeatureProductDTO;
import com.ecommerce.qp.inbound.messaging.version.IUtilsVersion;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CQRSUpdateProduct {

    @Autowired
    private Gson gson = new GsonBuilder()
            //.registerTypeAdapter(Product.class, new UpdateProductFeatureProductDTODeserializer())
            .create();

    @Autowired
    private ICommandProduct commandProduct;

    @Autowired
    private IUtilsVersion utilsVersion;

    @Autowired
    private ILogs logs;

    public void cqrsUpdateProduct(IMessage message){
        logs.logInfo("[CQRS] - Synchronization by update product command on cp service - message: " + message);
        Product product = null;
        try {
            Version version = utilsVersion.isNextVersion(message);
            product = IProductInMapper.toProduct(gson.fromJson(message.getData(), UpdateProductFeatureProductDTO.class));
            if(product.getCountermeasure().equals(CounterMeasure.UNLOCKED))
                try{
                    commandProduct.update(product.getId(), product);
                }catch (ProductNotExistException e){
                    commandProduct.create(product);
                }
            utilsVersion.nextVersion(version);
        } catch (ProductNotExistException e) {
            e.printStackTrace();
            logs.logError("[CQRS] - Product not exists - product: " + product);
            //  TODO Log this
        } catch (Exception e){
            e.printStackTrace();
            logs.logError("[CQRS] - Internal server error - product:" + product);
            //  TODO Log this
        }
    }
}
