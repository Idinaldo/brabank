package dev.idinaldo.brabank.auth.domain.exceptions;

public class IdentityNotFoundException extends RuntimeException {

    public IdentityNotFoundException(String email) {
        super("Identity not found for email: " + email);
    }
}