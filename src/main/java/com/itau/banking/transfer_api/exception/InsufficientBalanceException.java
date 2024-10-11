package com.itau.banking.transfer_api.exception;

import java.io.Serial;

public class InsufficientBalanceException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;


    public InsufficientBalanceException(String message) {
        super(message);
    }
}
