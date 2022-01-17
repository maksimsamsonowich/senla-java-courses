package com.github.exception.artist;

public class NoSuchArtistException extends RuntimeException {
    public NoSuchArtistException(String errorMessage) {
        super(errorMessage);
    }
}
