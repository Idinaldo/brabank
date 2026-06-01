package com.brabank.auth.application.usecases.impl;

import com.brabank.auth.adapters.in.dtos.LoginRequestDTO;
import com.brabank.auth.adapters.out.dtos.LoginResponseDTO;
import com.brabank.auth.application.ports.out.IdentityRepository;
import com.brabank.auth.domain.models.Identity;
import com.brabank.auth.domain.valueObjects.AccountStatus;
import com.brabank.auth.domain.valueObjects.Role;
import com.brabank.auth.infrastructure.exceptions.AccountNotActiveException;
import com.brabank.auth.infrastructure.exceptions.InvalidCredentialsException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LoginUseCaseImplTest {

    @Mock
    private IdentityRepository identityRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private LoginUseCaseImpl loginUseCase;

    private Identity createIdentity(AccountStatus status) {
        UUID id = UUID.randomUUID();
        Identity identity = new Identity(id, "user@brabank.com", "hashedPassword");
        identity.setStatus(status);
        identity.setRole(Role.CLIENT);
        return identity;
    }

    @Test
    void shouldLoginSuccessfully_whenCredentialsAreValidAndAccountIsActive() {
        // Arrange
        Identity identity = createIdentity(AccountStatus.ACTIVE);
        when(identityRepository.findByEmail("user@brabank.com")).thenReturn(Optional.of(identity));
        when(passwordEncoder.matches("rawPassword", "hashedPassword")).thenReturn(true);

        // Act
        LoginResponseDTO response = loginUseCase.execute(new LoginRequestDTO("user@brabank.com", "rawPassword"));

        // Assert
        assertNotNull(response);
        assertEquals(identity.getId(), response.id());
        assertEquals("user@brabank.com", response.email());
        assertEquals(AccountStatus.ACTIVE, response.status());
        assertEquals(Role.CLIENT, response.role());
        verify(identityRepository).findByEmail("user@brabank.com");
        verify(passwordEncoder).matches("rawPassword", "hashedPassword");
    }

    @Test
    void shouldThrowInvalidCredentials_whenEmailDoesNotExist() {
        // Arrange
        when(identityRepository.findByEmail("unknown@brabank.com")).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(InvalidCredentialsException.class,
                () -> loginUseCase.execute(new LoginRequestDTO("unknown@brabank.com", "password")));
        verify(identityRepository).findByEmail("unknown@brabank.com");
        verifyNoInteractions(passwordEncoder);
    }

    @Test
    void shouldThrowInvalidCredentials_whenPasswordDoesNotMatch() {
        // Arrange
        Identity identity = createIdentity(AccountStatus.ACTIVE);
        when(identityRepository.findByEmail("user@brabank.com")).thenReturn(Optional.of(identity));
        when(passwordEncoder.matches("wrongPassword", "hashedPassword")).thenReturn(false);

        // Act & Assert
        assertThrows(InvalidCredentialsException.class,
                () -> loginUseCase.execute(new LoginRequestDTO("user@brabank.com", "wrongPassword")));
    }

    @Test
    void shouldThrowAccountNotActive_whenAccountIsBlocked() {
        // Arrange
        Identity identity = createIdentity(AccountStatus.BLOCKED);
        when(identityRepository.findByEmail("user@brabank.com")).thenReturn(Optional.of(identity));
        when(passwordEncoder.matches("rawPassword", "hashedPassword")).thenReturn(true);

        // Act & Assert
        assertThrows(AccountNotActiveException.class,
                () -> loginUseCase.execute(new LoginRequestDTO("user@brabank.com", "rawPassword")));
    }

    @Test
    void shouldThrowAccountNotActive_whenAccountIsPendingVerification() {
        // Arrange
        Identity identity = createIdentity(AccountStatus.PENDING_VERIFICATION);
        when(identityRepository.findByEmail("user@brabank.com")).thenReturn(Optional.of(identity));
        when(passwordEncoder.matches("rawPassword", "hashedPassword")).thenReturn(true);

        // Act & Assert
        assertThrows(AccountNotActiveException.class,
                () -> loginUseCase.execute(new LoginRequestDTO("user@brabank.com", "rawPassword")));
    }
}
