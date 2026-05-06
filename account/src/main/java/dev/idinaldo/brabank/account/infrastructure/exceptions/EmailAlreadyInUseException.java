package dev.idinaldo.brabank.account.infrastructure.exceptions;

public class EmailAlreadyInUseException extends RuntimeException {
    public EmailAlreadyInUseException(String message) {
        super(message);
    }

    public EmailAlreadyInUseException() {
        super("The provided email is already in use");
    }
}
