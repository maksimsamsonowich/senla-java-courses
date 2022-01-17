package com.github.exception.role;

public class RoleRepetitionException extends RuntimeException {
    public RoleRepetitionException(String errorMessage) {
        super(errorMessage);
    }
}
