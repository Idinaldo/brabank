package dev.idinaldo.brabank.account.application.usecases.contracts;

import dev.idinaldo.brabank.account.domain.models.User;

public interface UserRegisterUseCase {
    public void execute(User user);
}
