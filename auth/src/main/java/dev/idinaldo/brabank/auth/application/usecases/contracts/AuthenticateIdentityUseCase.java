package dev.idinaldo.brabank.auth.application.usecases.contracts;

import dev.idinaldo.brabank.auth.adapters.in.dtos.LoginRequest;
import dev.idinaldo.brabank.auth.adapters.in.dtos.LoginResponse;

public interface AuthenticateIdentityUseCase {

    LoginResponse execute(LoginRequest loginRequest);
}