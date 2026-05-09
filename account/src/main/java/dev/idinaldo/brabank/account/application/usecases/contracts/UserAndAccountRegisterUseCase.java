package dev.idinaldo.brabank.account.application.usecases.contracts;

import dev.idinaldo.brabank.account.domain.models.User;

public interface UserAndAccountRegisterUseCase {
    public void execute(User user);
}
