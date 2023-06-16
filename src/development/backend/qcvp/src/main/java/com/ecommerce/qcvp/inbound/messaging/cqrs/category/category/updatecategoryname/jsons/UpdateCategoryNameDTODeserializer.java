package com.ecommerce.qcvp.inbound.messaging.cqrs.category.category.updatecategoryname.jsons;
/*
import com.ecommerce.qc.inbound.messaging.cqrs.category.category.updatecategoryname.mapper.dtos.UpdateCategoryNameDTO;
import com.google.gson.*;

import java.lang.reflect.Type;

public class UpdateCategoryNameDTODeserializer implements JsonDeserializer<UpdateCategoryNameDTO> {
    @Override
    public UpdateCategoryNameDTO deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        JsonObject jo = jsonElement.getAsJsonObject();
        UpdateCategoryNameDTO category = new UpdateCategoryNameDTO();
        if(jo.has("id")) category.setId(jo.get("id").getAsLong());
        if(jo.has("name")) category.setName(jo.get("name").getAsString());
        return category;
    }
}
*/