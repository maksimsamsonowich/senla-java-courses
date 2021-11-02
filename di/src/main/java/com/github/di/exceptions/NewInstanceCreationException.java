package com.github.di.exceptions;

public class NewInstanceCreationException extends Exception {
    public NewInstanceCreationException(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }

    public NewInstanceCreationException(String errorMessage) {
        super(errorMessage);
    }
}
