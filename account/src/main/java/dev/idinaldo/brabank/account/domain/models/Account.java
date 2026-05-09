package dev.idinaldo.brabank.account.domain.models;

import dev.idinaldo.brabank.account.domain.valueObjects.Status;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

public class Account {

    private UUID id = UUID.randomUUID();
    private UUID userId;
    private BigDecimal balance = new BigDecimal("0.00");
    private Status status = Status.PENDING_VERIFICATION;
    private String accountNumber;
    private String bankBranch;
    private Instant createdAt;
    private Instant updatedAt;

    public Account(UUID userId) {
        this.userId = userId;
    }

    public Account(UUID userId, String bankBranch) {
        this.userId = userId;
    }

    public void activateAccount() {
        this.status = Status.ACTIVE;
    }

    public void blockUser() {
        this.status = Status.BLOCKED;
    }

    public void deactivateUser() {
        this.status = Status.DEACTIVATED;
    }


    // getters and setters
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getBankBranch() {
        return bankBranch;
    }

    public void setBankBranch(String bankBranch) {
        this.bankBranch = bankBranch;
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
