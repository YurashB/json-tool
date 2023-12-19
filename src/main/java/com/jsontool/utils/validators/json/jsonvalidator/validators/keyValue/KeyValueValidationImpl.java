package com.jsontool.utils.validators.json.jsonvalidator.validators.keyValue;

import com.jsontool.utils.validators.json.jsonvalidator.exceptions.JSONValidationException;
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
                throw new JSONValidationException("Unexpected symbol at key", i);
            }

            ValueValidatorInterface valueValidator = new ValueValidatorImpl(this);

            i = valueValidator.validateValue(json, i);

            if (json[i] == ',') { // next key value pair is exist
                return validate(json, i + 1);
            } else if (json[i] == '}') { // end of json object
                return i;
            } else {
                throw new JSONValidationException("Unexpected symbol after key", i);
            }

        } else {
            throw new JSONValidationException("Unexpected symbol at key", i);
        }
    }
}
