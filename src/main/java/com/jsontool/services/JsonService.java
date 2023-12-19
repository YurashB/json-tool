package com.jsontool.services;

import org.springframework.stereotype.Service;

import com.jsontool.utils.validators.json.jsonvalidator.JsonValidator;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class JsonService {

    public final static String VALIDATION_SUCCESS_MESSAGE = "Json is valid";

    public String validate(String json) {
        JsonValidator jsonValidator = new JsonValidator();

        try {
            jsonValidator.validate(json);
        } catch (Exception e) {
            return e.getMessage();
        }

        return VALIDATION_SUCCESS_MESSAGE;
    }
}
