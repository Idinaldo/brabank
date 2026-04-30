package dev.idinaldo.brabank.auth.adapters.mapper;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import dev.idinaldo.brabank.auth.adapters.in.dtos.IdentityRequestDTO;
import dev.idinaldo.brabank.auth.adapters.out.JpaIdentity;
import dev.idinaldo.brabank.auth.adapters.out.dtos.IdentityResponseDTO;
import dev.idinaldo.brabank.auth.application.ports.IdentityMapper;
import dev.idinaldo.brabank.auth.domain.models.Identity;
import dev.idinaldo.brabank.auth.domain.models.IdentityRole;
import dev.idinaldo.brabank.auth.domain.models.IdentityStatus;

@Component
public class IdentityMapperImpl implements IdentityMapper {

    private final PasswordEncoder passwordEncoder;

    public IdentityMapperImpl(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public Identity requestDtoToDomain(IdentityRequestDTO identityRequestDTO) {
        return Identity.builder()
                .email(identityRequestDTO.email())
                .passwordHash(passwordEncoder.encode(identityRequestDTO.password()))
                .status(IdentityStatus.PENDING_VERIFICATION)
                .role(IdentityRole.CLIENT)
                .build();
    }

    @Override
    public IdentityResponseDTO domainToResponseDto(Identity identity) {
        return new IdentityResponseDTO(identity.getId(), identity.getEmail(), identity.getStatus());
    }

    @Override
    public JpaIdentity domainToJpaEntity(Identity identity) {
        return JpaIdentity.builder()
                .id(identity.getId())
                .email(identity.getEmail())
                .passwordHash(identity.getPasswordHash())
                .status(identity.getStatus())
                .role(identity.getRole())
                .createdAt(identity.getCreatedAt())
                .updatedAt(identity.getUpdatedAt())
                .build();
    }

    @Override
    public Identity jpaEntityToDomain(JpaIdentity jpaIdentity) {
        return Identity.builder()
                .id(jpaIdentity.getId())
                .email(jpaIdentity.getEmail())
                .passwordHash(jpaIdentity.getPasswordHash())
                .status(jpaIdentity.getStatus())
                .role(jpaIdentity.getRole())
                .createdAt(jpaIdentity.getCreatedAt())
                .updatedAt(jpaIdentity.getUpdatedAt())
                .build();
    }
}
