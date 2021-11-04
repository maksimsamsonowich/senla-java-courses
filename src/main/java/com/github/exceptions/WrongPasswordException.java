package com.github.exceptions;

public class WrongPasswordException extends RuntimeException {
    public WrongPasswordException(String errorMsg) {
        super(errorMsg);
    }
}
