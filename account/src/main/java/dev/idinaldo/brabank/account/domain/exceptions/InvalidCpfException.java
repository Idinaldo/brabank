package dev.idinaldo.brabank.account.infrastructure.exceptions;

public class InvalidCpfException extends RuntimeException {

    public InvalidCpfException(String message) {
        super(message);
    }

    public InvalidCpfException() {
        super("The provided CPF is invalid");
    }
}
