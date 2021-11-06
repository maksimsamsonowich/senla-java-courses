package com.github.exceptions.user;

public class WrongPasswordException extends RuntimeException {
    public WrongPasswordException(String errorMsg) {
        super(errorMsg);
    }
}
