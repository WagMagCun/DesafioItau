package com.itau.banking.transfer_api.resource.repository;

import com.itau.banking.transfer_api.resource.dto.TransferRequestDTO;
import com.itau.banking.transfer_api.resource.dto.TransferResponseDTO;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class TransferClientMockImpl implements ITransferClientMock {

    @Override
    public TransferResponseDTO transfer(TransferRequestDTO transferRequestDTO) {
        UUID uuid = UUID.randomUUID();
        TransferResponseDTO transferResponseDTO = new TransferResponseDTO();
        transferResponseDTO.setIdTransferencia(uuid);
        return transferResponseDTO;
    }

}
