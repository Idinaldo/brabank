package dev.idinaldo.brabank.account.domain.valueObjects;

public enum Status {
    PENDING_KYC_VERIFICATION, // registered but didn't complete KYC
    ACTIVE, // regular account with KYC complete
    DEACTIVATED, // regular account that was deactivated by client
    BLOCKED // brabank blocked client account
}
