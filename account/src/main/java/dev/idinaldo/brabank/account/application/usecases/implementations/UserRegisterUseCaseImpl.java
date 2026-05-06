package dev.idinaldo.brabank.account.application.usecases.implementations;

import dev.idinaldo.brabank.account.application.usecases.contracts.UserRegisterUseCase;
import dev.idinaldo.brabank.account.domain.models.User;
import dev.idinaldo.brabank.account.infrastructure.exceptions.CpfAlreadyInUseException;
import dev.idinaldo.brabank.account.infrastructure.exceptions.EmailAlreadyInUseException;
import dev.idinaldo.brabank.account.infrastructure.persistence.UserRepositoryPort;

// TODO: review exceptions as exposing
//  registered data is a bad cybersecurity practice
public class UserRegisterUseCaseImpl implements UserRegisterUseCase {

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
