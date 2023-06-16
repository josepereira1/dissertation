package com.ecommerce.qcap.inbound.messaging.cqrs.category.category.createcategories.jsons;
/*
import com.ecommerce.qc.inbound.messaging.cqrs.category.category.createcategories.mapper.in.dtos.CreateCategoriesDTO;
import com.google.gson.*;
import java.lang.reflect.Type;

public class CreateCategoriesDTODeserializer implements JsonDeserializer<CreateCategoriesDTO> {
    @Override
    public CreateCategoriesDTO deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        JsonObject jo = jsonElement.getAsJsonObject();
        CreateCategoriesDTO category = new CreateCategoriesDTO();
        if(jo.has("id")) category.setId(jo.get("id").getAsLong());
        if(jo.has("name")) category.setName(jo.get("name").getAsString());
        if(jo.has("countermeasure")) category.setCountermeasure(jo.get("countermeasure").getAsString().charAt(0));
        return category;
    }
}
*/