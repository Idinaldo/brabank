package dev.idinaldo.brabank.account.domain.exceptions;

public class InvalidCpfException extends RuntimeException {

    public InvalidCpfException(String message) {
        super(message);
    }

    public InvalidCpfException() {
        super("The provided CPF is invalid");
    }
}
