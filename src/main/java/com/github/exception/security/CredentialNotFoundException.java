package com.github.exception.security;

public class CredentialNotFoundException extends RuntimeException {

    public CredentialNotFoundException(String errorMessage) {
        super(errorMessage);
    }

}
