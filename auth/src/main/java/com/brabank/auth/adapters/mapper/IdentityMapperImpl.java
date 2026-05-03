package dev.idinaldo.brabank.auth.adapters.mapper;

import dev.idinaldo.brabank.auth.adapters.in.dtos.IdentityRequestDTO;
import dev.idinaldo.brabank.auth.adapters.out.JpaIdentity;
import dev.idinaldo.brabank.auth.adapters.out.dtos.IdentityResponseDTO;
import dev.idinaldo.brabank.auth.application.ports.IdentityMapper;
import dev.idinaldo.brabank.auth.domain.models.Identity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class IdentityMapperImpl implements IdentityMapper {

    private final PasswordEncoder passwordEncoder;

    public IdentityMapperImpl(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public Identity requestDtoToDomain(IdentityRequestDTO identityRequestDTO) {
        Identity identity = new Identity();

        String passwordHash = passwordEncoder.encode(identityRequestDTO.password());

        identity.setPasswordHash(passwordHash);
        identity.setEmail(identityRequestDTO.email());

        return identity;
    }

    @Override
    public IdentityResponseDTO domainToResponseDto(Identity identity) {
        return new IdentityResponseDTO(identity.getId(), identity.getEmail(), identity.getStatus());
    }

    @Override
    public JpaIdentity domainToJpaEntity(Identity identity) {

        JpaIdentity jpaIdentity = new JpaIdentity();

        jpaIdentity.setId(identity.getId());
        jpaIdentity.setEmail(identity.getEmail());
        jpaIdentity.setPasswordHash(identity.getPasswordHash());
        jpaIdentity.setStatus(identity.getStatus());
        jpaIdentity.setRole(identity.getRole());
        jpaIdentity.setCreatedAt(identity.getCreatedAt());
        jpaIdentity.setUpdatedAt(identity.getUpdatedAt());

        return jpaIdentity;
    }
        @Override
    public Identity jpaEntityToDomain(JpaIdentity jpaIdentity) {
        Identity identity = new Identity();

        identity.setId(jpaIdentity.getId());
        identity.setEmail(jpaIdentity.getEmail());
        identity.setPasswordHash(jpaIdentity.getPasswordHash());
        identity.setStatus(jpaIdentity.getStatus());
        identity.setRole(jpaIdentity.getRole());
        identity.setCreatedAt(jpaIdentity.getCreatedAt());
        identity.setUpdatedAt(jpaIdentity.getUpdatedAt());

        return identity;
    }
}
