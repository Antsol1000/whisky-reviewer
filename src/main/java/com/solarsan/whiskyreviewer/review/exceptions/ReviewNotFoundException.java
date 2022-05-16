package com.solarsan.whiskyreviewer.review.exceptions;

public class ReviewNotFoundException extends RuntimeException {
    public ReviewNotFoundException(final String name) {
        super(String.format("Review with id %s not found", name));
    }
}
