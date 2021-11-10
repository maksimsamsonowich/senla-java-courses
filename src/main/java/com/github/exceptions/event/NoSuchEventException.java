package com.github.exceptions.event;

public class NoSuchEventException extends RuntimeException {
    public NoSuchEventException(String errorMsg) {
        super(errorMsg);
    }
}
