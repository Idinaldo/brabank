package dev.idinaldo.brabank.account.domain.exceptions;

public class CpfAlreadyInUseException extends RuntimeException {

    public CpfAlreadyInUseException(String message) {
        super(message);
    }

    public CpfAlreadyInUseException() {
        super("The provided CPF is already in use");
    }
}
