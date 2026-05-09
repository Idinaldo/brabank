package dev.idinaldo.brabank.account.infrastructure.persistence;

import dev.idinaldo.brabank.account.domain.models.Account;

import java.util.UUID;

public interface AccountRepositoryPort {

    Account save(Account account);
    Account findByAccountNumber(String accountNumber);
    Account findById(UUID id);
}
