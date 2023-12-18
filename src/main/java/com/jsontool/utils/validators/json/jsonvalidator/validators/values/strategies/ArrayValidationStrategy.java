package com.jsontool.utils.validators.json.jsonvalidator.validators.values.strategies;

import com.jsontool.utils.validators.json.jsonvalidator.exceptions.JSONValidationException;
import com.jsontool.utils.validators.json.jsonvalidator.validators.values.ValueValidatorInterface;

public class ArrayValidationStrategy implements JSONValidationValueStrategy {

    private final ValueValidatorInterface valueValidator;

    public ArrayValidationStrategy(ValueValidatorInterface valueValidator) {
        this.valueValidator = valueValidator;
    }

    @Override
    public int validate(char[] json, int i) {
        while (json[i] != ']') {
            i = valueValidator.validateValue(json, i);

            if (json[i] != ',' && json[i] != ']') {
                throw new JSONValidationException("Expected ']' or invalid symbol entered", i + 1);
            } else if (json[i] == ',' && json[i + 1] == ']') {
                throw new JSONValidationException("Expected value after ',' at but nothing provided", i + 1);
            } else if (json[i] == ',') {
                i++;
            }
        }

        return i + 1;
    }
}
