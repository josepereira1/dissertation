package com.ecommerce.cp.inbound.messaging.cqrs.product.updatestockstatus;

import com.ecommerce.cp.core.business.exceptions.product.ProductWithThatIdNotExistException;
import com.ecommerce.cp.core.business.logs.Logs;
import com.ecommerce.cp.core.business.messaging.resources.IMessage;
import com.ecommerce.cp.core.business.resources.product.Product;
import com.ecommerce.cp.core.ports.in.product.ICommandProduct;
import com.ecommerce.cp.inbound.messaging.cqrs.product.updatestockstatus.jsons.UpdateStockStatusDTODeserializer;
import com.ecommerce.cp.inbound.messaging.cqrs.product.updatestockstatus.mappers.in.IProductInMapper;
import com.ecommerce.cp.inbound.messaging.cqrs.product.updatestockstatus.mappers.in.dtos.UpdateStockStatusDTO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CQRSUpdateStockStatus {

    @Autowired
    private Gson gson = new GsonBuilder()
            .registerTypeAdapter(Product.class, new UpdateStockStatusDTODeserializer())
            .create();

    @Autowired
    private Logs logs;

    @Autowired
    private ICommandProduct commandProduct;

    public void cqrsUpdateStockStatus(IMessage message){
        logs.logInfo("[CQRS] - Synchronization by update stock status command in the inventory service - message: " + message);
        Product product = null;
        try {
            product = IProductInMapper.toProduct(gson.fromJson(message.getData(), UpdateStockStatusDTO.class));
            commandProduct.updateProduct(product.getId(), product);
        } catch (ProductWithThatIdNotExistException e) {
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
