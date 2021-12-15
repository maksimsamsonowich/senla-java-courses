package com.github.exception.entities;

public class NoSuchEntityException extends RuntimeException {
    public NoSuchEntityException(String errorMsg) {
        super(errorMsg);
    }
}
