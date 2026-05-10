package dev.idinaldo.brabank.account.application.usecases.implementations;

import dev.idinaldo.brabank.account.application.ports.persistence.IAccountRepository;
import dev.idinaldo.brabank.account.application.usecases.contracts.IUserAndAccountRegisterUseCase;
import dev.idinaldo.brabank.account.domain.models.Account;
import dev.idinaldo.brabank.account.domain.models.User;
import dev.idinaldo.brabank.account.domain.exceptions.CpfAlreadyInUseException;
import dev.idinaldo.brabank.account.domain.exceptions.EmailAlreadyInUseException;
import dev.idinaldo.brabank.account.application.ports.persistence.IUserRepository;

// TODO: review exceptions for cybersecurity concerns (account mapping, data exposure)
public class UserAndAccountRegisterUseCaseImpl implements IUserAndAccountRegisterUseCase {

    private IUserRepository userRepository;
    private IAccountRepository accountRepository;

    @Override
    public void execute(User user) {
        if (userRepository.existsByCpf(user.getCpf())) {
            throw new CpfAlreadyInUseException();
        } else if (userRepository.existsByEmail(user.getEmail())) {
            throw new EmailAlreadyInUseException();
        }
        user = userRepository.save(user);
        accountRepository.save(new Account(user.getId()));
    }
}
