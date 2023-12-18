package com.jsontool.utils.validators.json.jsonvalidator.validators.keyValue;

import com.jsontool.utils.validators.json.jsonvalidator.exceptions.JSONException;
import com.jsontool.utils.validators.json.jsonvalidator.validators.values.ValueValidatorImpl;
import com.jsontool.utils.validators.json.jsonvalidator.validators.values.ValueValidatorInterface;

public class KeyValueValidationImpl implements KeyValueValidatorInterface {

    @Override
    public int validate(char[] json, int i) {
        if (json[i] == '\"') {
            i++;
            while (json[i] != '\"') {
                i++;
            }
            i++;

            if (json[i++] != ':') {
                throw new JSONException(
                        String.format("Expected a ':' after key at %s [character %s line %s]", i - 1, i, 0));
            }

            ValueValidatorInterface valueValidator = new ValueValidatorImpl(this);

            i = valueValidator.validateValue(json, i);

            if (json[i] == ',') { // next key value pair is exist
                return validate(json, i + 1);
            } else if (json[i] == '}') { // end of json object
                return i;
            } else {
                throw new JSONException(
                        String.format("Expected a ',' after key at %s [character %s line %s]", i - 1, i, 0));
            }

        } else {
            throw new JSONException(
                    String.format("Expected a '\"' after key at %s [character %s line %s]", i - 1, i, 0));
        }
    }
}
