package dev.idinaldo.brabank.auth.application.ports.out;

import dev.idinaldo.brabank.auth.domain.models.Identity;

import java.util.UUID;

public interface IdentityRepository {

    Identity save(Identity identity);
    Identity findById(UUID id);
}
