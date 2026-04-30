package dev.idinaldo.brabank.auth.domain.models;

import java.time.Instant;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "passwordHash")
public class Identity {

    private UUID id;
    private String email;
    private String passwordHash;

    @Builder.Default
    private IdentityStatus status = IdentityStatus.PENDING_VERIFICATION;

    @Builder.Default
    private IdentityRole role = IdentityRole.CLIENT;

    private Instant createdAt;
    private Instant updatedAt;

    public void blockAccount() {
        this.status = IdentityStatus.BLOCKED;
    }

    public void deactivateAccount() {
        this.status = IdentityStatus.DEACTIVATED;
    }

    public void activateAccount() {
        this.status = IdentityStatus.ACTIVE;
    }
}
