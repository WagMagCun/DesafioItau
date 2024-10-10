package com.itau.banking.transfer_api.application.web.controller;

import com.itau.banking.transfer_api.application.web.request.TransferRequest;
import com.itau.banking.transfer_api.application.web.response.TransferResponse;
import com.itau.banking.transfer_api.domain.service.contract.ITransferService;
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
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<TransferResponse> transfer(@RequestBody TransferRequest request) {

        TransferResponse transferResponse = TransferResponse.fromDomain(transferenciaService.transfer(request.toModel())) ; //@@
        return ResponseEntity.status(HttpStatus.CREATED).body(transferResponse);


    }
}
