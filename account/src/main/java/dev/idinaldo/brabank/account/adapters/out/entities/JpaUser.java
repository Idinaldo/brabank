package dev.idinaldo.brabank.account.adapters.out.entities;

import dev.idinaldo.brabank.account.domain.valueObjects.CPF;
import dev.idinaldo.brabank.account.domain.valueObjects.PhoneNumber;
import dev.idinaldo.brabank.account.domain.valueObjects.Status;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "users")
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class JpaUser {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotNull
    @NotEmpty
    private String fullName;

    @NotNull
    private LocalDate birthDate;

    @NotNull
    @NotEmpty
    @Column(nullable = false, unique = true)
    private String email;

    @NotNull
    @Column(nullable = false, unique = true)
    private PhoneNumber phoneNumber;

    @NotNull
    @Column(nullable = false, unique = true)
    private CPF cpf;

    @NotNull
    private Status status = Status.PENDING_KYC_VERIFICATION;

    @CreationTimestamp
    private Instant createdAt;
    @UpdateTimestamp
    private Instant updatedAt;
}
