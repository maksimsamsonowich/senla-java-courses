package com.github.di.exceptions;

import java.io.IOException;

public class NoSuchFileException extends IOException {
    public NoSuchFileException(String errorMessage, Throwable err){
        super(errorMessage, err);
    }

    public NoSuchFileException(String errorMessage) {
        super(errorMessage);
    }
}
