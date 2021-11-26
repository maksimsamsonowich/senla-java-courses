package com.github.exceptions.entities;

public class NoSuchEntityException extends RuntimeException {
    public NoSuchEntityException(String errorMsg) {
        super(errorMsg);
    }
}
