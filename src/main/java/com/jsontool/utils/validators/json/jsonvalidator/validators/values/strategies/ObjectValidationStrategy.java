package com.jsontool.utils.validators.json.jsonvalidator.validators.values.strategies;

import com.jsontool.utils.validators.json.jsonvalidator.validators.keyValue.KeyValueValidatorInterface;

public class ObjectValidationStrategy implements JSONValidationValueStrategy {

    private final KeyValueValidatorInterface keyValueValidator;

    public ObjectValidationStrategy(KeyValueValidatorInterface keyValueValidator) {
        this.keyValueValidator = keyValueValidator;
    }

    @Override
    public int validate(char[] json, int i) {
        while (json[i] != '}') {
            i = keyValueValidator.validate(json, i);
        }

        if (i == json.length - 1) {
            return i;
        } else {
            return i + 1;
        }
    }
}
