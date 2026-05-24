package com.brabank.auth.adapters.out.persistance;

import com.brabank.auth.adapters.out.JpaIdentity;
import com.brabank.auth.application.ports.IdentityMapper;
import com.brabank.auth.domain.models.Identity;
import com.brabank.auth.domain.valueObjects.AccountStatus;
import com.brabank.auth.domain.valueObjects.Role;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class IdentityRepositoryImplTest {

    @Mock
    private IdentityMapper identityMapper;

    @Mock
    private IdentityJpaRepository identityJpaRepository;

    @InjectMocks
    private IdentityRepositoryImpl identityRepository;

    @Test
    void findByEmail_shouldReturnIdentity_whenEmailExists() {
        // Arrange
        String email = "user@brabank.com";
        UUID id = UUID.randomUUID();

        JpaIdentity jpaIdentity = new JpaIdentity(id, email, "hashedPw", AccountStatus.ACTIVE, Role.CLIENT);
        Identity domainIdentity = new Identity(id, email, "hashedPw");
        domainIdentity.setStatus(AccountStatus.ACTIVE);

        when(identityJpaRepository.findByEmail(email)).thenReturn(Optional.of(jpaIdentity));
        when(identityMapper.jpaEntityToDomain(jpaIdentity)).thenReturn(domainIdentity);

        // Act
        Optional<Identity> result = identityRepository.findByEmail(email);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(email, result.get().getEmail());
        assertEquals(id, result.get().getId());
        verify(identityJpaRepository).findByEmail(email);
        verify(identityMapper).jpaEntityToDomain(jpaIdentity);
    }

    @Test
    void findByEmail_shouldReturnEmpty_whenEmailDoesNotExist() {
        // Arrange
        String email = "nonexistent@brabank.com";
        when(identityJpaRepository.findByEmail(email)).thenReturn(Optional.empty());

        // Act
        Optional<Identity> result = identityRepository.findByEmail(email);

        // Assert
        assertTrue(result.isEmpty());
        verify(identityJpaRepository).findByEmail(email);
        verifyNoInteractions(identityMapper);
    }
}
