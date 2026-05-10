package dev.idinaldo.brabank.account.adapters.out.persistence;

import dev.idinaldo.brabank.account.application.ports.persistence.IAccountRepository;
import dev.idinaldo.brabank.account.domain.models.Account;

import java.util.UUID;

public class AccountRepositoryImpl implements IAccountRepository {

    private AccountJpaRepository accountJpaRepository;

    @Override
    public Account save(Account account) {
        return null;
    }

    @Override
    public Account findByAccountNumber(String accountNumber) {
        return null;
    }

    @Override
    public Account findById(UUID id) {
        return null;
    }
}
