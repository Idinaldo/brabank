package com.brabank.auth.application.ports;

import com.brabank.auth.adapters.in.dtos.IdentityRequestDTO;
import com.brabank.auth.adapters.out.JpaIdentity;
import com.brabank.auth.adapters.out.dtos.IdentityResponseDTO;
import com.brabank.auth.domain.models.Identity;

public interface IdentityMapper {

    Identity requestDtoToDomain(IdentityRequestDTO identityRequestDTO);
    IdentityResponseDTO domainToResponseDto(Identity identity);
    JpaIdentity domainToJpaEntity(Identity identity);
    Identity jpaEntityToDomain(JpaIdentity jpaIdentity);
}
