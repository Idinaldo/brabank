package dev.idinaldo.brabank.auth.adapters.out.persistance;

import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import dev.idinaldo.brabank.auth.adapters.out.JpaIdentity;
import dev.idinaldo.brabank.auth.application.ports.IdentityMapper;
import dev.idinaldo.brabank.auth.application.ports.out.IdentityRepository;
import dev.idinaldo.brabank.auth.domain.models.Identity;

@Repository
public class IdentityRepositoryImpl implements IdentityRepository {

    private final IdentityMapper identityMapper;
    private final IdentityJpaRepository identityJpaRepository;

    public IdentityRepositoryImpl(IdentityMapper identityMapper, dev.idinaldo.brabank.auth.adapters.out.persistance.IdentityJpaRepository identityJpaRepository) {
        this.identityMapper = identityMapper;
        this.identityJpaRepository = identityJpaRepository;
    }

    @Override
    public Identity save(Identity identity) {
        JpaIdentity jpaIdentity = this.identityMapper.domainToJpaEntity(identity);
        JpaIdentity persistedJpaIdentity = identityJpaRepository.save(jpaIdentity);
        return identityMapper.jpaEntityToDomain(persistedJpaIdentity);
    }

    @Override
    public Optional<Identity> findById(UUID id) {
        return identityJpaRepository.findById(id).map(identityMapper::jpaEntityToDomain);
    }

    @Override
    public Optional<Identity> findByEmail(String email) {
        return identityJpaRepository.findByEmail(email).map(identityMapper::jpaEntityToDomain);
    }
}
