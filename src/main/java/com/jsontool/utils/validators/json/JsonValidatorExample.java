package com.jsontool.utils.validators.json;

public class JsonValidatorExample implements JsonValidator {
    /**
     * It is example class of Json validator to show Strategy pattern
     * Not accepted to use
     * @return always false
     */

    @Deprecated
    @Override
    public boolean isValid(String json) {
        return false;
    }
}
