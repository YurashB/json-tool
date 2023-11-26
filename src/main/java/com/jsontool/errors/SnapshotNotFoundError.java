package com.jsontool.errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class SnapshotNotFoundError extends ResponseStatusException {
    public SnapshotNotFoundError() {
        super(HttpStatus.NOT_FOUND, "Snapshot not found");
    }
}
