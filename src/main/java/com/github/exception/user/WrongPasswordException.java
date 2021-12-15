package com.github.exception.user;

public class WrongPasswordException extends RuntimeException {
    public WrongPasswordException(String errorMsg) {
        super(errorMsg);
    }
}
