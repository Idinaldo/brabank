package com.brabank.auth.application.usecases.contracts;

import com.brabank.auth.adapters.in.dtos.IdentityRequestDTO;
import com.brabank.auth.adapters.out.dtos.IdentityResponseDTO;

public interface RegisterUseCase {
    IdentityResponseDTO execute(IdentityRequestDTO identityRequestDTO);
}
