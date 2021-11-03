package com.github.di.exceptions;

public class NoAccessException extends RuntimeException {
    public NoAccessException(String errorMessage) {
        super(errorMessage);
    }

}
