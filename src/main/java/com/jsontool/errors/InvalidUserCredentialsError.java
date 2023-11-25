package com.jsontool.errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class InvalidUserCredentialsError extends ResponseStatusException {
    public InvalidUserCredentialsError() {
        super(HttpStatus.BAD_REQUEST, "Invalid credentials provided");
    }
}
