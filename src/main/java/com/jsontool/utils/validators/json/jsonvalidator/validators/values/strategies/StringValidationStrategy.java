package com.jsontool.utils.validators.json.jsonvalidator.validators.values.strategies;

public class StringValidationStrategy implements JSONValidationValueStrategy {
    @Override
    public int validate(char[] json, int i) {
        while (json[i] != '\"') {
            i++;
        }

        return i + 1;
    }
}
