package dev.idinaldo.brabank.account.application.ports.persistence;

import dev.idinaldo.brabank.account.domain.models.Account;

import java.util.UUID;

public interface IAccountRepository {

    Account save(Account account);
    Account findByAccountNumber(String accountNumber);
    Account findById(UUID id);
}
