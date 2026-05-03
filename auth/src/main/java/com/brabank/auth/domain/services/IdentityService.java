package dev.idinaldo.brabank.auth.domain.services;

import dev.idinaldo.brabank.auth.adapters.in.dtos.IdentityRequestDTO;
import dev.idinaldo.brabank.auth.adapters.out.dtos.IdentityResponseDTO;
import dev.idinaldo.brabank.auth.application.usecases.contracts.RegisterUseCase;

public class IdentityService implements RegisterUseCase {

    @Override
    public IdentityResponseDTO execute(IdentityRequestDTO identityRequestDTO) {
        return null;
    }
}
