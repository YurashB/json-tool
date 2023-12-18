package com.jsontool.utils.validators.json.jsonvalidator.validators.values;

import com.jsontool.utils.validators.json.jsonvalidator.exceptions.JSONValidationException;
import com.jsontool.utils.validators.json.jsonvalidator.validators.keyValue.KeyValueValidatorInterface;
import com.jsontool.utils.validators.json.jsonvalidator.validators.values.strategies.ArrayValidationStrategy;
import com.jsontool.utils.validators.json.jsonvalidator.validators.values.strategies.FalseValidationStrategy;
import com.jsontool.utils.validators.json.jsonvalidator.validators.values.strategies.NullValidationStrategy;
import com.jsontool.utils.validators.json.jsonvalidator.validators.values.strategies.NumberValidationStrategy;
import com.jsontool.utils.validators.json.jsonvalidator.validators.values.strategies.ObjectValidationStrategy;
import com.jsontool.utils.validators.json.jsonvalidator.validators.values.strategies.StringValidationStrategy;
import com.jsontool.utils.validators.json.jsonvalidator.validators.values.strategies.TrueValidationStrategy;

public class ValueValidatorImpl implements ValueValidatorInterface {

    private final KeyValueValidatorInterface keyValueValidator;
    private final JSONValidationContext validationContext = new JSONValidationContext();

    public ValueValidatorImpl(KeyValueValidatorInterface keyValueValidator) {
        this.keyValueValidator = keyValueValidator;
    }

    @Override
    public int validateValue(char[] json, int i) {
        if (json[i] == '\"') {
            validationContext.setValidationValueStrategy(new StringValidationStrategy());
        } else if (Character.isDigit(json[i]) || json[i] == '-') {
            validationContext.setValidationValueStrategy(new NumberValidationStrategy());
            return validationContext.validateValue(json, i);
        } else if (json[i] == 't') {
            validationContext.setValidationValueStrategy(new TrueValidationStrategy());
        } else if (json[i] == 'f') {
            validationContext.setValidationValueStrategy(new FalseValidationStrategy());
        } else if (json[i] == 'n') {
            validationContext.setValidationValueStrategy(new NullValidationStrategy());
        } else if (json[i] == '[') {
            validationContext.setValidationValueStrategy(new ArrayValidationStrategy(this));
        } else if (json[i] == '{') {
            validationContext.setValidationValueStrategy(new ObjectValidationStrategy(keyValueValidator));
        } else {
            throw new JSONValidationException("Expected a '\"' after key", i);
        }

        return validationContext.validateValue(json, i + 1);
    }
}
