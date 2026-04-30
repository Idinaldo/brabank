package dev.idinaldo.brabank.auth.application.usecases.impl;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import dev.idinaldo.brabank.auth.adapters.in.dtos.LoginRequest;
import dev.idinaldo.brabank.auth.adapters.in.dtos.LoginResponse;
import dev.idinaldo.brabank.auth.application.ports.out.GeneratedToken;
import dev.idinaldo.brabank.auth.application.ports.out.IdentityRepository;
import dev.idinaldo.brabank.auth.application.ports.out.TokenProvider;
import dev.idinaldo.brabank.auth.application.usecases.contracts.AuthenticateIdentityUseCase;
import dev.idinaldo.brabank.auth.domain.exceptions.IdentityNotActiveException;
import dev.idinaldo.brabank.auth.domain.exceptions.IdentityNotFoundException;
import dev.idinaldo.brabank.auth.domain.exceptions.InvalidCredentialsException;
import dev.idinaldo.brabank.auth.domain.models.Identity;
import dev.idinaldo.brabank.auth.domain.models.IdentityStatus;

@Component
public class AuthenticateIdentityUseCaseImpl implements AuthenticateIdentityUseCase {

    private final IdentityRepository identityRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenProvider tokenProvider;

    public AuthenticateIdentityUseCaseImpl(IdentityRepository identityRepository,
                                           PasswordEncoder passwordEncoder,
                                           TokenProvider tokenProvider) {
        this.identityRepository = identityRepository;
        this.passwordEncoder = passwordEncoder;
        this.tokenProvider = tokenProvider;
    }

    @Override
    public LoginResponse execute(LoginRequest loginRequest) {
        Identity identity = identityRepository.findByEmail(loginRequest.email())
                .orElseThrow(() -> new IdentityNotFoundException(loginRequest.email()));

        if (identity.getStatus() != IdentityStatus.ACTIVE) {
            throw new IdentityNotActiveException(identity.getStatus());
        }

        if (!passwordEncoder.matches(loginRequest.password(), identity.getPasswordHash())) {
            throw new InvalidCredentialsException();
        }

        GeneratedToken generatedToken = tokenProvider.generateToken(identity);

        return new LoginResponse(
            generatedToken.accessToken(),
                "Bearer",
            generatedToken.expiresIn()
        );
    }
}