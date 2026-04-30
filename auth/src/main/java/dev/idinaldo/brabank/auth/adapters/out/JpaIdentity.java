package dev.idinaldo.brabank.auth.adapters.out;

import java.time.Instant;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import dev.idinaldo.brabank.auth.domain.models.IdentityRole;
import dev.idinaldo.brabank.auth.domain.models.IdentityStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "identities")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JpaIdentity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String email;

    @Column(name = "password_hash", nullable = false, length = 60)
    private String passwordHash;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, name = "status")
    @Builder.Default
    private IdentityStatus status = IdentityStatus.PENDING_VERIFICATION;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, name = "role")
    @Builder.Default
    private IdentityRole role = IdentityRole.CLIENT;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private Instant updatedAt;
}
