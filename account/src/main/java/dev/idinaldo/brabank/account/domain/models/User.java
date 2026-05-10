package dev.idinaldo.brabank.account.domain.models;

import dev.idinaldo.brabank.account.domain.valueObjects.CPF;
import dev.idinaldo.brabank.account.domain.valueObjects.PhoneNumber;
import dev.idinaldo.brabank.account.domain.valueObjects.Status;

import java.time.Instant;
import java.time.LocalDate;
import java.util.UUID;

public class User {

    private UUID id;
    private String fullName;
    private LocalDate birthDate;
    private String email;
    private PhoneNumber phoneNumber;
    private CPF cpf;
    private Status status = Status.PENDING_KYC_VERIFICATION;
    private Instant createdAt;
    private Instant updatedAt;

    public User(UUID id, String fullName, LocalDate birthDate, String email, PhoneNumber phoneNumber, CPF cpf, Status status, Instant createdAt, Instant updatedAt) {
        this.id = id;
        this.fullName = fullName;
        this.birthDate = birthDate;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.cpf = cpf;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public User(String fullName, LocalDate birthDate, String email, PhoneNumber phoneNumber, CPF cpf) {
        this.fullName = fullName;
        this.birthDate = birthDate;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.cpf = cpf;
    }

    public User() {

    }

    public void activateUser() {
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

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public PhoneNumber getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(PhoneNumber phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public CPF getCpf() {
        return cpf;
    }

    public void setCpf(CPF cpf) {
        this.cpf = cpf;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
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
