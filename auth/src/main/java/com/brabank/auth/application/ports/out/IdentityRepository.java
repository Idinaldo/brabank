package com.brabank.auth.application.ports.out;

import com.brabank.auth.domain.models.Identity;

import java.util.UUID;

public interface IdentityRepository {

    Identity save(Identity identity);
    Identity findById(UUID id);
}
