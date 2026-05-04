package com.brabank.auth.infrastructure.exceptions;

public class InvalidEmailException extends RuntimeException {
    public InvalidEmailException() {
        super("The provided e-mail is invalid");
    }
}
