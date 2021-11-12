package com.github.exceptions.database;

public class DatabaseAccessException extends RuntimeException {
    public DatabaseAccessException(String errorMessage) {
        super(errorMessage);
    }
}
