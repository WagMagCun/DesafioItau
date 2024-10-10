package com.itau.banking.transfer_api.resource.repository;

import com.itau.banking.transfer_api.resource.dto.TransferRequestDTO;
import com.itau.banking.transfer_api.resource.dto.TransferResponseDTO;
import org.springframework.web.bind.annotation.GetMapping;


public interface ITransferClientMock {

    @GetMapping("/transferencia/{id}")
    TransferResponseDTO transfer(TransferRequestDTO transferRequestDTO);

}
