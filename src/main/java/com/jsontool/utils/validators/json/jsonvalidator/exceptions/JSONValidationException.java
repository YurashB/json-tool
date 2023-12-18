package com.jsontool.utils.validators.json.jsonvalidator.exceptions;

public class JSONValidationException extends RuntimeException {

    private final int formattedCharPosition;

    public JSONValidationException(final String message, int formattedCharPosition) {
        super(message);
        this.formattedCharPosition = formattedCharPosition;
    }

    public int getFormattedCharPosition() {
        return formattedCharPosition;
    }
}
