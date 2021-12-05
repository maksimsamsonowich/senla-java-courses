package com.github.exception.database;

public class DatabaseCloseConnectionException extends RuntimeException {
    public DatabaseCloseConnectionException(String errorMsg) {
        super(errorMsg);
    }
}
