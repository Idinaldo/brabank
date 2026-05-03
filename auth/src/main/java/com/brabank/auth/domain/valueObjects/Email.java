package dev.idinaldo.brabank.auth.domain.valueObjects;

import dev.idinaldo.brabank.auth.infrastructure.exceptions.InvalidEmailException;

public class Email {

    private final String email;

    public Email(String email) {
        if (!email.matches("^[\\w\\-.]+@([\\w-]+\\.)+[\\w-]{2,}$")) {
            throw new InvalidEmailException();
        }
        this.email = email;
    }

    public String getEmail() {
        return email;
    }
}
