package dev.idinaldo.brabank.auth.application.ports.out;

import java.util.Optional;
import java.util.UUID;

import dev.idinaldo.brabank.auth.domain.models.Identity;

public interface IdentityRepository {

    Identity save(Identity identity);
    Optional<Identity> findById(UUID id);
    Optional<Identity> findByEmail(String email);
}
