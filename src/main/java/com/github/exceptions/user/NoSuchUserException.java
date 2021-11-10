package com.github.exceptions.user;

public class NoSuchUserException extends RuntimeException {

    public NoSuchUserException(String errorMsg) {
        super(errorMsg);
    }
}
