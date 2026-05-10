package dev.idinaldo.brabank.account.domain.exceptions;

public class InvalidPhoneNumberException extends RuntimeException {

    public InvalidPhoneNumberException(String message) {
        super(message);
    }

    public InvalidPhoneNumberException() {
        super("The provided phone number is valid. Please try again");
    }
}
