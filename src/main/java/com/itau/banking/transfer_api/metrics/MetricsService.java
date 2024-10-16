package com.itau.banking.transfer_api.metrics;

public interface MetricsService {
    void incrementInsufficientBalance();
    void incrementDailyLimitExceeded();
    void incrementAccountInactive();
    void incrementClientNotFound();
    void incrementTransferFailed();
}