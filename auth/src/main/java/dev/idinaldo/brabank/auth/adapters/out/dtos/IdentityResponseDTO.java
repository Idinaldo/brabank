package dev.idinaldo.brabank.auth.adapters.out.dtos;

import java.util.UUID;

import dev.idinaldo.brabank.auth.domain.models.IdentityStatus;

public record IdentityResponseDTO(UUID id, String email, IdentityStatus accountStatus) {
}
