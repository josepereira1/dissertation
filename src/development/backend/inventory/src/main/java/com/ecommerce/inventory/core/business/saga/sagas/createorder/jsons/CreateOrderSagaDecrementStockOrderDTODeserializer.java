package com.ecommerce.inventory.core.business.saga.sagas.createorder.jsons;
/*
import com.ecommerce.inventory.core.business.saga.sagas.createorder.mappers.in.dtos.CreateOrderSagaDecrementStockOrderDTO;
import com.ecommerce.inventory.core.business.saga.sagas.createorder.mappers.in.dtos.CreateOrderSagaDecrementStockProductDTO;
import com.google.gson.*;
import java.lang.reflect.Type;
import java.util.Arrays;

public class CreateOrderSagaDecrementStockOrderDTODeserializer implements JsonDeserializer<CreateOrderSagaDecrementStockOrderDTO> {
    @Override
    public CreateOrderSagaDecrementStockOrderDTO deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        JsonObject jo = jsonElement.getAsJsonObject();
        CreateOrderSagaDecrementStockOrderDTO order = new CreateOrderSagaDecrementStockOrderDTO();
        if(jo.has("id")) order.setId(jo.get("id").getAsString());
        if(jo.has("products")) order.setProducts(Arrays.asList(jsonDeserializationContext.deserialize(jo.get("products"), CreateOrderSagaDecrementStockProductDTO[].class)));
        return order;
    }
}*/
