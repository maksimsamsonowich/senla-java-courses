package com.github.exception.database;

public class DatabaseAccessException extends RuntimeException {
    public DatabaseAccessException(String errorMessage) {
        super(errorMessage);
    }
}
