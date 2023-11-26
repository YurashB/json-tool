package com.jsontool.utils.validators.json;

import org.json.JSONException;
import org.json.JSONObject;

public class StrictJsonValidator implements JsonValidator {

    @Override
    public boolean isValid(String json) {
        try {
            new JSONObject(json);
        } catch (JSONException e) {
            return false;
        }
        return true;
    }
}
