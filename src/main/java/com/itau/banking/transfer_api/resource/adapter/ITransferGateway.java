package com.itau.banking.transfer_api.resource.adapter;

import com.itau.banking.transfer_api.domain.model.TransferModel;
import com.itau.banking.transfer_api.resource.dto.AccountResponseDTO;
import com.itau.banking.transfer_api.resource.dto.ClientResponseDTO;
import com.itau.banking.transfer_api.resource.dto.TransferResponseDTO;
import org.springframework.http.ResponseEntity;

public interface ITransferGateway {

    TransferResponseDTO transfer(TransferModel transferModel);
    AccountResponseDTO searchAccountById(String id);
    ClientResponseDTO searchClientById(String id);
    ResponseEntity<Void> updateBalance(TransferModel transferModel);
    ResponseEntity<Void> notifyBacen(TransferModel transferModel);

}