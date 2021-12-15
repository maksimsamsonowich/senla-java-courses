package com.github.exception.event;

public class NoSuchEventException extends RuntimeException {
    public NoSuchEventException(String errorMsg) {
        super(errorMsg);
    }
}
