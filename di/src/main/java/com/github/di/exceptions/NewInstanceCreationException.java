package com.github.di.exceptions;

public class NewInstanceCreationException extends RuntimeException {
    public NewInstanceCreationException(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }

    public NewInstanceCreationException(String errorMessage) {
        super(errorMessage);
    }
}
