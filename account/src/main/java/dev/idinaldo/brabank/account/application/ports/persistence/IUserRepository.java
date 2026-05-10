package dev.idinaldo.brabank.account.application.ports.persistence;

import dev.idinaldo.brabank.account.domain.models.User;
import dev.idinaldo.brabank.account.domain.valueObjects.CPF;

public interface IUserRepository {
    User save(User user);
    boolean existsByCpf(CPF cpf);
    boolean existsByEmail(String email);
}
