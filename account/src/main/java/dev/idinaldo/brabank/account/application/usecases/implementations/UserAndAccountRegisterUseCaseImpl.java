package dev.idinaldo.brabank.account.application.usecases.implementations;

import dev.idinaldo.brabank.account.application.usecases.contracts.UserAndAccountRegisterUseCase;
import dev.idinaldo.brabank.account.domain.models.User;
import dev.idinaldo.brabank.account.infrastructure.exceptions.CpfAlreadyInUseException;
import dev.idinaldo.brabank.account.infrastructure.exceptions.EmailAlreadyInUseException;
import dev.idinaldo.brabank.account.infrastructure.persistence.UserRepositoryPort;

// TODO: review exceptions for cybersecurity concerns (account mapping, data exposure)
public class UserAndAccountRegisterUseCaseImpl implements UserAndAccountRegisterUseCase {

    private UserRepositoryPort userRepositoryPort;

    @Override
    public void execute(User user) {
        if (userRepositoryPort.existsByCpf(user.getCpf())) {
            throw new CpfAlreadyInUseException();
        } else if (userRepositoryPort.existsByEmail(user.getEmail())) {
            throw new EmailAlreadyInUseException();
        }
        userRepositoryPort.save(user);
    }
}
