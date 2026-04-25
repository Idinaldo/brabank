package dev.idinaldo.brabank.auth.application.usecases;

import dev.idinaldo.brabank.auth.adapters.in.dtos.IdentityRequestDTO;
import dev.idinaldo.brabank.auth.adapters.out.dtos.IdentityResponseDTO;

public interface RegisterUseCase {
    IdentityResponseDTO register(IdentityRequestDTO identityRequestDTO);
}
