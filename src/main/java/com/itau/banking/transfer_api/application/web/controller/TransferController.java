package com.itau.banking.transfer_api.application.web.controller;

import com.itau.banking.transfer_api.application.web.request.TransferRequest;
import com.itau.banking.transfer_api.application.web.response.TransferResponse;
import com.itau.banking.transfer_api.domain.contract.ITransferService;
import com.itau.banking.transfer_api.domain.exception.AccountInactiveException;
import com.itau.banking.transfer_api.domain.exception.ClientNotFoundException;
import com.itau.banking.transfer_api.domain.exception.DailyLimitExceededException;
import com.itau.banking.transfer_api.domain.exception.InsufficientBalanceException;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/transferencia")
public class TransferController {

    private final ITransferService transferenciaService;

    public TransferController(ITransferService transferService) {
        this.transferenciaService = transferService;
    }

    @PostMapping
    public ResponseEntity<TransferResponse> transfer(@Valid @RequestBody TransferRequest request)
            throws ClientNotFoundException, InsufficientBalanceException, AccountInactiveException, DailyLimitExceededException {

        TransferResponse transferResponse = TransferResponse.fromDomain(transferenciaService.transfer(request.toModel()));
        return ResponseEntity.status(HttpStatus.OK).body(transferResponse);

    }
}
