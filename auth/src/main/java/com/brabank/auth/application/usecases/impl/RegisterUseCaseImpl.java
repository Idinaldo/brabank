package com.brabank.auth.application.usecases.impl;

import com.brabank.auth.adapters.in.dtos.IdentityRequestDTO;
import com.brabank.auth.adapters.out.dtos.IdentityResponseDTO;
import com.brabank.auth.application.ports.IdentityMapper;
import com.brabank.auth.application.ports.out.IdentityRepository;
import com.brabank.auth.application.usecases.contracts.RegisterUseCase;
import com.brabank.auth.domain.models.Identity;
import org.springframework.stereotype.Component;

@Component
public class RegisterUseCaseImpl implements RegisterUseCase {

    private final IdentityMapper identityMapper;
    private final IdentityRepository identityRepository;

    public RegisterUseCaseImpl(IdentityMapper identityMapper, IdentityRepository identityRepository) {
        this.identityMapper = identityMapper;
        this.identityRepository = identityRepository;
    }

    @Override
    public IdentityResponseDTO execute(IdentityRequestDTO identityRequestDTO) {
        Identity identity = identityMapper.requestDtoToDomain(identityRequestDTO);
        Identity registeredIdentity = identityRepository.save(identity);
        return identityMapper.domainToResponseDto(registeredIdentity);
    }
}
