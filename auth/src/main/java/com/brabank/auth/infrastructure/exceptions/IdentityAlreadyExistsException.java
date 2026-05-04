package com.brabank.auth.infrastructure.exceptions;

public class IdentityAlreadyExistsException extends RuntimeException {

    public IdentityAlreadyExistsException() {
        super("Please verify your information and try again.");
    }

    public IdentityAlreadyExistsException(String message) {
        super(message);
    }
}
