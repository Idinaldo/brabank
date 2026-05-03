package dev.idinaldo.brabank.auth.application.ports;

import dev.idinaldo.brabank.auth.adapters.in.dtos.IdentityRequestDTO;
import dev.idinaldo.brabank.auth.adapters.out.JpaIdentity;
import dev.idinaldo.brabank.auth.adapters.out.dtos.IdentityResponseDTO;
import dev.idinaldo.brabank.auth.domain.models.Identity;

public interface IdentityMapper {

    Identity requestDtoToDomain(IdentityRequestDTO identityRequestDTO);
    IdentityResponseDTO domainToResponseDto(Identity identity);
    JpaIdentity domainToJpaEntity(Identity identity);
    Identity jpaEntityToDomain(JpaIdentity jpaIdentity);
}
