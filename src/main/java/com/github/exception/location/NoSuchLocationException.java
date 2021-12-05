package com.github.exception.location;

public class NoSuchLocationException extends RuntimeException {
    public NoSuchLocationException(String errorMsg) {
        super(errorMsg);
    }
}
