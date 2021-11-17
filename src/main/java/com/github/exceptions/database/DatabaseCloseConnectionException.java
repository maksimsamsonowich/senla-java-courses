package com.github.exceptions.database;

public class DatabaseCloseConnectionException extends RuntimeException {
    public DatabaseCloseConnectionException(String errorMsg) {
        super(errorMsg);
    }
}
