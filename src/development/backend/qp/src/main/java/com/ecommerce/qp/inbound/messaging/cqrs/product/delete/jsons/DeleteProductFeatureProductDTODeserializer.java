package com.ecommerce.qp.inbound.messaging.cqrs.product.delete.jsons;
/*
import com.ecommerce.qp.inbound1.messaging.cqrs.product.delete.mappers.in.dtos.DeleteProductFeatureProductDTO;
import com.google.gson.*;
import java.lang.reflect.Type;

public class DeleteProductFeatureProductDTODeserializer implements JsonDeserializer<DeleteProductFeatureProductDTO> {
    @Override
    public DeleteProductFeatureProductDTO deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        DeleteProductFeatureProductDTO product = new DeleteProductFeatureProductDTO();
        JsonObject jo = jsonElement.getAsJsonObject();

        if(jo.has("id")) product.setId(jo.get("id").getAsString());
        return product;
    }
}
*/