package com.github.exception.role;

public class RoleNotFoundException extends RuntimeException {
    public RoleNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
