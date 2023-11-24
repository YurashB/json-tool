package com.jsontool.errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class InvalidUserCredentialsException extends ResponseStatusException {
    public InvalidUserCredentialsException() {
        super(HttpStatus.BAD_REQUEST, "Invalid credentials provided");
    }
}
