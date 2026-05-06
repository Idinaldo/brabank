package dev.idinaldo.brabank.account.infrastructure.persistence;

import dev.idinaldo.brabank.account.domain.models.User;
import dev.idinaldo.brabank.account.domain.valueObjects.CPF;

public interface UserRepositoryPort {
    public void save(User user);
    public boolean existsByCpf(CPF cpf);
    public boolean existsByEmail(String email);
}
