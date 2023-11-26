package com.jsontool.utils.validators.json;


import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

public class NonStrictJsonValidator implements  JsonValidator {
    @Override
    public boolean isValid(String json) {
        try {
            JsonParser.parseString(json);
        } catch (JsonSyntaxException e) {
            return false;
        }
        return true;
    }
}
