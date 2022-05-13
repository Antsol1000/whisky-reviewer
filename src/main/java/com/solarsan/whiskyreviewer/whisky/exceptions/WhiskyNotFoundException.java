package com.solarsan.whiskyreviewer.whisky.exceptions;

public class WhiskyNotFoundException extends RuntimeException {
    public WhiskyNotFoundException(final String name) {
        super(String.format("Whisky with id %s not found", name));
    }
}
