package com.ecommerce.cp.inbound.messaging.cqrs.product.updatestockstatus.jsons;

import com.ecommerce.cp.core.business.resources.product.StockStatus;
import com.ecommerce.cp.inbound.messaging.cqrs.product.updatestockstatus.mappers.in.dtos.UpdateStockStatusDTO;
import com.google.gson.*;
import java.lang.reflect.Type;

public class UpdateStockStatusDTODeserializer implements JsonDeserializer<UpdateStockStatusDTO> {
    @Override
    public UpdateStockStatusDTO deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        JsonObject jo = jsonElement.getAsJsonObject();
        UpdateStockStatusDTO product = new UpdateStockStatusDTO();
        if(jo.has("id")) product.setId(jo.get("id").getAsString());
        if(jo.has("stockStatus")) product.setStockStatus(StockStatus.getStatus(jo.get("stockStatus").getAsString()));
        return product;
    }
}
