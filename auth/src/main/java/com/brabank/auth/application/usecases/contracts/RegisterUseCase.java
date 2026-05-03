package dev.idinaldo.brabank.auth.application.usecases.contracts;

import dev.idinaldo.brabank.auth.adapters.in.dtos.IdentityRequestDTO;
import dev.idinaldo.brabank.auth.adapters.out.dtos.IdentityResponseDTO;

public interface RegisterUseCase {
    IdentityResponseDTO execute(IdentityRequestDTO identityRequestDTO);
}
