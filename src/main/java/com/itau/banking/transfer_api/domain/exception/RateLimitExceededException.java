package com.itau.banking.transfer_api.domain.exception;

import java.io.Serial;

public class RateLimitExceededException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    public RateLimitExceededException(String message) {
        super(message);
    }
}
