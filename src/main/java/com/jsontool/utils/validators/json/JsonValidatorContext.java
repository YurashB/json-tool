package com.jsontool.utils.validators.json;

public class JsonValidatorContext {
    private JsonValidator validator;

    public void setValidator(JsonValidator validator) {
        this.validator = validator;
    }

    public boolean validateJson(String json) {
        return validator.isValid(json);
    }
}
