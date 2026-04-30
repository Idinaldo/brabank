package dev.idinaldo.brabank.auth.domain.exceptions;

public class InvalidCredentialsException extends RuntimeException {

    public InvalidCredentialsException() {
        super("Invalid email or password.");
    }
}