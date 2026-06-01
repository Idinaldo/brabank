package com.brabank.auth.adapters.out.dtos;

import com.brabank.auth.domain.valueObjects.AccountStatus;
import com.brabank.auth.domain.valueObjects.Role;

import java.util.UUID;

public record LoginResponseDTO(UUID id, String email, AccountStatus status, Role role) {
}
