package com.itau.banking.transfer_api.domain.exception;

import java.io.Serial;

public class DailyLimitExceededException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    public DailyLimitExceededException(String message) {
        super(message);
    }
}
