package com.brabank.auth.adapters.out.persistance;

import com.brabank.auth.adapters.out.JpaIdentity;
import com.brabank.auth.application.ports.IdentityMapper;
import com.brabank.auth.application.ports.out.IdentityRepository;
import com.brabank.auth.domain.models.Identity;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public class IdentityRepositoryImpl implements IdentityRepository {

    private final IdentityMapper identityMapper;
    private final IdentityJpaRepository identityJpaRepository;

    public IdentityRepositoryImpl(IdentityMapper identityMapper, IdentityJpaRepository identityJpaRepository) {
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
    public Identity findById(UUID id) {
        return null;
    }
}
