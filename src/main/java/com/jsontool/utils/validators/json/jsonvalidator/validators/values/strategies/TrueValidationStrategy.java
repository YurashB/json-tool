package com.jsontool.utils.validators.json.jsonvalidator.validators.values.strategies;

import com.jsontool.utils.validators.json.jsonvalidator.exceptions.JSONValidationException;

public class TrueValidationStrategy implements JSONValidationValueStrategy {

    @Override
    public int validate(char[] json, int i) {
        char r = json[i];
        char u = json[i + 1];
        char e = json[i + 2];

        if (r != 'r' || u != 'u' || e != 'e') {
            throw new JSONValidationException("Unexpected symbol after key", i);
        }

        return i + 3;
    }
}
