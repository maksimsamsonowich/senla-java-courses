package com.github.di.exceptions;

public class NoSuchFileException extends RuntimeException {
    public NoSuchFileException(String errorMessage, Throwable err){
        super(errorMessage, err);
    }

    public NoSuchFileException(String errorMessage) {
        super(errorMessage);
    }
}
