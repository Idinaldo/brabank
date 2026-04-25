package dev.idinaldo.brabank.auth.domain.models;

import dev.idinaldo.brabank.auth.domain.valueObjects.AccountStatus;
import dev.idinaldo.brabank.auth.domain.valueObjects.Email;

import java.util.UUID;

public class Identity {

    private UUID id;
    private Email email;
    private String passwordHash;
    private AccountStatus status = AccountStatus.VERIFICATION_PENDING;

    public Identity(UUID id, String email, String passwordHash) {
        this.id = id;
        this.email = new Email(email);
        this.passwordHash = passwordHash;
    }

    public Identity(String passwordHash, String email) {
        this.passwordHash = passwordHash;
        this.email = new Email(email);
    }

    public void blockAccount() {
        this.status = AccountStatus.BLOCKED;
    }

    public void activateAccount() {
        this.status = AccountStatus.ACTIVE;
    }

    // getters and setters
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getEmail() {
        return email.getEmail();
    }

    public void setEmail(String email) {
        this.email = new Email(email);
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public AccountStatus getStatus() {
        return status;
    }

    public void setStatus(AccountStatus status) {
        this.status = status;
    }
}
