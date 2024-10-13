package com.itau.banking.transfer_api.domain.exception;

import java.io.Serial;

public class AccountInactiveException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1L;

    public AccountInactiveException(String message) {
        super(message);
    }
}
