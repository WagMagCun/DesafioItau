package com.itau.banking.transfer_api.resource.adapter;

import com.itau.banking.transfer_api.domain.model.TransferModel;
import com.itau.banking.transfer_api.resource.dto.AccountResponseDTO;
import com.itau.banking.transfer_api.resource.dto.ClientResponseDTO;
import com.itau.banking.transfer_api.resource.dto.TransferResponseDTO;

public interface ITransferGateway {

    TransferResponseDTO transfer(TransferModel transferModel);
    AccountResponseDTO searchAccountById(String id);
    ClientResponseDTO searchClientById(String id);

}