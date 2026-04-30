package dev.idinaldo.brabank.auth.domain.exceptions;

import dev.idinaldo.brabank.auth.domain.models.IdentityStatus;

public class IdentityNotActiveException extends RuntimeException {

    public IdentityNotActiveException(IdentityStatus status) {
        super(buildMessage(status));
    }

    private static String buildMessage(IdentityStatus status) {
        if (status == IdentityStatus.BLOCKED) {
            return "Identity is blocked.";
        }

        if (status == IdentityStatus.DEACTIVATED) {
            return "Identity is deactivated.";
        }

        if (status == IdentityStatus.PENDING_VERIFICATION) {
            return "Identity is pending verification.";
        }

        return "Identity is not active.";
    }
}