package com.backend.api.exceptions;

public class DataIntegrityException extends RuntimeException {
    public DataIntegrityException(String msg) {
        super(msg);
    }
}
