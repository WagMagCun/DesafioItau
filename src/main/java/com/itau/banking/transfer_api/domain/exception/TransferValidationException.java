package com.itau.banking.transfer_api.domain.exception;

import java.io.Serial;

public class TransferValidationException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1L;

    public TransferValidationException(String message) {
        super(message);
    }
}
