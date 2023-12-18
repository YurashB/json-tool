package com.jsontool.utils.validators.json.jsonvalidator.validators.values.strategies;

public class NumberValidationStrategy implements JSONValidationValueStrategy {
    @Override
    public int validate(char[] json, int i) {
        if (json[i] == '-') {
            i++;
        }

        while ((Character.isDigit(json[i]))) {
            i++;
        }

        return i;
    }
}
