package com.example.ecommerce.saga.inbound;

import org.everit.json.schema.ValidationException;
import org.everit.json.schema.loader.SchemaLoader;
import org.json.JSONObject;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class JsonValidator {
    public static void validateJson(String schemaFile, JSONObject jsonToValidate) throws IOException, ValidationException {
        JSONObject jsonSchema = new JSONObject(new String(Files.readAllBytes(Paths.get(schemaFile))));
        SchemaLoader.load(jsonSchema).validate(jsonToValidate);
    }
}
