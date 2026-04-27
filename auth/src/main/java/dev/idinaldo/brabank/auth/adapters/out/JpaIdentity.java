package dev.idinaldo.brabank.auth.adapters.out;

import dev.idinaldo.brabank.auth.domain.valueObjects.AccountStatus;
import dev.idinaldo.brabank.auth.domain.valueObjects.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "identities")
@Getter
@Setter
public class JpaIdentity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotNull
    @NotEmpty
    @Column(nullable = false)
    private String email;

    @NotNull
    @NotEmpty
    @Column(nullable = false)
    private String passwordHash;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, name = "status")
    private AccountStatus status;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, columnDefinition = "role")
    private Role role;

    @CreationTimestamp
    private Instant createdAt;

    @UpdateTimestamp
    private Instant updatedAt;

    public JpaIdentity() {
    }

    public JpaIdentity(UUID id, String email, String passwordHash, AccountStatus status, Role role) {
        this.id = id;
        this.email = email;
        this.passwordHash = passwordHash;
        this.status = status;
        this.role = role;
    }
}
