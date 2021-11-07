package com.github.exceptions.location;

public class NoSuchLocationException extends RuntimeException {
    public NoSuchLocationException(String errorMsg) {
        super(errorMsg);
    }
}
