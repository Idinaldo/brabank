package dev.idinaldo.brabank.auth.adapters.out.dtos;

import dev.idinaldo.brabank.auth.domain.valueObjects.AccountStatus;

import java.util.UUID;

public record IdentityResponseDTO(UUID id, String email, AccountStatus accountStatus) {
}
