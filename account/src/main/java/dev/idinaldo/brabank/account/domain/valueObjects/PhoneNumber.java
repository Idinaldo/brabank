package dev.idinaldo.brabank.account.domain.valueObjects;

import dev.idinaldo.brabank.account.infrastructure.exceptions.InvalidPhoneNumberException;

public record PhoneNumber(String phoneNumber) {

    public PhoneNumber(String phoneNumber) {
        if (this.normalize(phoneNumber).matches("\\d{2}9\\d{8}")) {
            this.phoneNumber = phoneNumber;
        }
        throw new InvalidPhoneNumberException();
    }

    private String normalize(String phoneNumber) {
        return phoneNumber.replaceAll("[()\\s-]", "");
    }
}
