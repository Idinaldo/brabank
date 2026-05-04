package com.brabank.auth.adapters.out.dtos;

import com.brabank.auth.domain.valueObjects.AccountStatus;

import java.util.UUID;

public record IdentityResponseDTO(UUID id, String email, AccountStatus accountStatus) {
}
