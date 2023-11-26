package com.jsontool.errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class JsonNotValidError extends ResponseStatusException {
    public JsonNotValidError() {
        super(HttpStatus.BAD_REQUEST, "Invalid json provided");
    }
}