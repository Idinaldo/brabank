package dev.idinaldo.brabank.account.adapters.out.entities;

import dev.idinaldo.brabank.account.domain.valueObjects.Status;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "accounts")
@Getter
@Setter
@RequiredArgsConstructor
// TODO: review userId definition and annotations
public class JpaAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "user_id")
    @Column(nullable = false)
    private UUID userId;

    @NotNull
    @Column(nullable = false)
    private BigDecimal balance = new BigDecimal("0.00");

    @NotNull
    @Column(nullable = false)
    private Status status = Status.PENDING_KYC_VERIFICATION;

    @NotNull
    @NotEmpty
    @Column(nullable = false, unique = true)
    private String accountNumber;

    @NotNull
    @NotEmpty
    @Column(nullable = false)
    private String branchCode;

    @CreationTimestamp
    private Instant createdAt;
    @UpdateTimestamp
    private Instant updatedAt;
}
