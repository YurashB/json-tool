package com.jsontool.utils.validators.json.jsonvalidator.validators.values.strategies;

import com.jsontool.utils.validators.json.jsonvalidator.exceptions.JSONValidationException;

public class NullValidationStrategy implements JSONValidationValueStrategy {
    @Override
    public int validate(char[] json, int i) {
        char u = json[i];
        char l = json[i + 1];
        char l2 = json[i + 2];

        if (u != 'u' || l != 'l' || l2 != 'l') {
            throw new JSONValidationException("Unexpected symbol after key", i);
        }

        return i + 3;
    }
}
