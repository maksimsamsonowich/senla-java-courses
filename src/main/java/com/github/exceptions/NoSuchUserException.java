package com.github.exceptions;

public class NoSuchUserException extends RuntimeException {

    public NoSuchUserException(String errorMsg) {
        super(errorMsg);
    }
}
