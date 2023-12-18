package com.jsontool.utils.validators.json.jsonvalidator.validators.values;

import com.jsontool.utils.validators.json.jsonvalidator.validators.values.strategies.JSONValidationValueStrategy;

public class JSONValidationContext {
    private JSONValidationValueStrategy validationValueStrategy;

    public void setValidationValueStrategy(JSONValidationValueStrategy validationValueStrategy) {
        this.validationValueStrategy = validationValueStrategy;
    }

    public int validateValue(char[] json, int i) {
        return validationValueStrategy.validate(json, i);
    }
}
