package com.solarsan.whiskyreviewer.reviewer.exceptions;

public class ReviewerNotFoundException extends RuntimeException {
    public ReviewerNotFoundException(final String name) {
        super(String.format("Reviewer with id %s not found", name));
    }
}
