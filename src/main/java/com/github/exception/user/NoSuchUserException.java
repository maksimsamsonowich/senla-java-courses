package com.github.exception.user;

public class NoSuchUserException extends RuntimeException {

    public NoSuchUserException(String errorMsg) {
        super(errorMsg);
    }
}
