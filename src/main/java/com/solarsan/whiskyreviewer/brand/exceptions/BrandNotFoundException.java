package com.solarsan.whiskyreviewer.brand.exceptions;

public class BrandNotFoundException extends RuntimeException {
    public BrandNotFoundException(final String name) {
        super(String.format("Brand with id %s not found", name));
    }
}
