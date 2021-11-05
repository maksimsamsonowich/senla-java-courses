package com.github.exceptions;

public class NoSuchEntityException extends RuntimeException {
    public NoSuchEntityException(String errorMsg) {
        super(errorMsg);
    }
}
