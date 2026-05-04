package com.brabank.auth.domain.services;

import com.brabank.auth.adapters.in.dtos.IdentityRequestDTO;
import com.brabank.auth.adapters.out.dtos.IdentityResponseDTO;
import com.brabank.auth.application.usecases.contracts.RegisterUseCase;

public class IdentityService implements RegisterUseCase {

    @Override
    public IdentityResponseDTO execute(IdentityRequestDTO identityRequestDTO) {
        return null;
    }
}
