package com.github.di.exceptions;

public class NoSuchImplementation extends RuntimeException {

    public NoSuchImplementation(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }

    public NoSuchImplementation(String errorMessage) {
        super(errorMessage);
    }

}
