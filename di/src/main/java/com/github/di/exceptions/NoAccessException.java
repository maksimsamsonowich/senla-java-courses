package com.github.di.exceptions;

public class NoAccessException extends IllegalAccessException {
    public NoAccessException(String errorMessage) {
        super(errorMessage);
    }
}
