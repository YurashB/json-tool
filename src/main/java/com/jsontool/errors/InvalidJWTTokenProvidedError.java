package com.jsontool.errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class InvalidJWTTokenProvidedError extends ResponseStatusException {
    public InvalidJWTTokenProvidedError() {
        super(HttpStatus.BAD_REQUEST, "Invalid JWT token provided");
    }
}
