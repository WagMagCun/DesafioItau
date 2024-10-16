package com.itau.banking.transfer_api.metrics;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.stereotype.Service;

@Service
public class MetricsServiceImpl implements MetricsService {

    private final Counter insufficientBalanceCounter;
    private final Counter dailyLimitExceededCounter;
    private final Counter accountInactiveCounter;
    private final Counter clientNotFoundCounter;
    private final Counter transferFailedCounter;

    public MetricsServiceImpl(MeterRegistry meterRegistry) {
        this.insufficientBalanceCounter = meterRegistry.counter("transfer.insufficient_balance");
        this.dailyLimitExceededCounter = meterRegistry.counter("transfer.daily_limit_exceeded");
        this.accountInactiveCounter = meterRegistry.counter("transfer.account_inactive");
        this.clientNotFoundCounter = meterRegistry.counter("transfer.client_not_found");
        this.transferFailedCounter = meterRegistry.counter("transfer.failed");
    }

    @Override
    public void incrementInsufficientBalance() {
        insufficientBalanceCounter.increment();
    }

    @Override
    public void incrementDailyLimitExceeded() {
        dailyLimitExceededCounter.increment();
    }

    @Override
    public void incrementAccountInactive() {
        accountInactiveCounter.increment();
    }

    @Override
    public void incrementClientNotFound() {
        clientNotFoundCounter.increment();
    }

    @Override
    public void incrementTransferFailed() {
        transferFailedCounter.increment();
    }
}
