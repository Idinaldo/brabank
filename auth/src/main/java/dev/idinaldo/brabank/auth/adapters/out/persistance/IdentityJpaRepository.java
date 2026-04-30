package dev.idinaldo.brabank.auth.adapters.out.persistance;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dev.idinaldo.brabank.auth.adapters.out.JpaIdentity;

@Repository
public interface IdentityJpaRepository extends JpaRepository<JpaIdentity, UUID> {

	Optional<JpaIdentity> findByEmail(String email);
}
