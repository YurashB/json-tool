package com.jsontool.errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class UserAlreadyExistError extends ResponseStatusException {
    public UserAlreadyExistError(String email) {
        super(HttpStatus.BAD_REQUEST, "User with email [" + email + "] already exist");
    }
}
