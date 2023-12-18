package com.jsontool.utils.validators.json.jsonvalidator.validators.values.strategies;

import com.jsontool.utils.validators.json.jsonvalidator.exceptions.JSONValidationException;

public class FalseValidationStrategy implements JSONValidationValueStrategy {
    @Override
    public int validate(char[] json, int i) {
        char a = json[i];
        char l = json[i + 1];
        char s = json[i + 2];
        char e = json[i + 3];

        if (a != 'a' || l != 'l' || s != 's' || e != 'e') {
            throw new JSONValidationException("Unexpected symbol after key", i);
        } else {
            return i + 4; // return next symbol after false 'e'
        }
    }
}
