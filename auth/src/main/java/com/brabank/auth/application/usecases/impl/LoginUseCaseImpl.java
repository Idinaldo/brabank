package com.brabank.auth.application.usecases.impl;

import com.brabank.auth.adapters.in.dtos.LoginRequestDTO;
import com.brabank.auth.adapters.out.dtos.LoginResponseDTO;
import com.brabank.auth.application.ports.out.IdentityRepository;
import com.brabank.auth.application.usecases.contracts.LoginUseCase;
import com.brabank.auth.domain.models.Identity;
import com.brabank.auth.domain.valueObjects.AccountStatus;
import com.brabank.auth.infrastructure.exceptions.AccountNotActiveException;
import com.brabank.auth.infrastructure.exceptions.InvalidCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class LoginUseCaseImpl implements LoginUseCase {

    private final IdentityRepository identityRepository;
    private final PasswordEncoder passwordEncoder;

    public LoginUseCaseImpl(IdentityRepository identityRepository, PasswordEncoder passwordEncoder) {
        this.identityRepository = identityRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public LoginResponseDTO execute(LoginRequestDTO loginRequestDTO) {
        Identity identity = identityRepository.findByEmail(loginRequestDTO.email())
                .orElseThrow(InvalidCredentialsException::new);

        if (!passwordEncoder.matches(loginRequestDTO.password(), identity.getPasswordHash())) {
            throw new InvalidCredentialsException();
        }

        if (identity.getStatus() != AccountStatus.ACTIVE) {
            throw new AccountNotActiveException();
        }

        return new LoginResponseDTO(
                identity.getId(),
                identity.getEmail(),
                identity.getStatus(),
                identity.getRole()
        );
    }
}
