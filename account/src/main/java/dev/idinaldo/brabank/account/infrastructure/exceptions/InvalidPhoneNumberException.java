package dev.idinaldo.brabank.account.infrastructure.exceptions;

public class InvalidPhoneNumberException extends RuntimeException {

    public InvalidPhoneNumberException(String message) {
        super(message);
    }

    public InvalidPhoneNumberException() {
        super("The provided phone number is valid. Please try again");
    }
}
