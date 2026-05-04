package com.brabank.auth.domain.models;

import com.brabank.auth.domain.valueObjects.AccountStatus;
import com.brabank.auth.domain.valueObjects.Email;
import com.brabank.auth.domain.valueObjects.Role;

import java.time.Instant;
import java.util.UUID;

public class Identity {

    private UUID id;
    private Email email;
    private String passwordHash;
    private AccountStatus status = AccountStatus.PENDING_VERIFICATION;
    private Role role = Role.CLIENT;
    private Instant createdAt;
    private Instant updatedAt;

    public Identity(UUID id, String email, String passwordHash) {
        this.id = id;
        this.email = new Email(email);
        this.passwordHash = passwordHash;
    }

    public Identity(String passwordHash, String email) {
        this.passwordHash = passwordHash;
        this.email = new Email(email);
    }

    public Identity() {
    }

    public Identity(UUID id, Email email, String passwordHash, AccountStatus accountStatus, Role role) {
        this.id = id;
        this.email = email;
        this.passwordHash = passwordHash;
        this.status = accountStatus;
        this.role = role;
    }

    public Identity(UUID id, Email email, String passwordHash, AccountStatus accountStatus, Role role, Instant createdAt, Instant updatedAt) {
        this.id = id;
        this.email = email;
        this.passwordHash = passwordHash;
        this.status = accountStatus;
        this.role = role;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }
}
