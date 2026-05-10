package dev.idinaldo.brabank.account.adapters.out.persistence;

import dev.idinaldo.brabank.account.adapters.out.entities.JpaAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AccountJpaRepository extends JpaRepository<JpaAccount, UUID> {
}
