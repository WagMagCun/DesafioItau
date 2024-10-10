package com.itau.banking.transfer_api.resource.adapter;

import com.itau.banking.transfer_api.common.LogTags;
import com.itau.banking.transfer_api.domain.model.TransferModel;
import com.itau.banking.transfer_api.resource.dto.AccountResponseDTO;
import com.itau.banking.transfer_api.resource.dto.ClientResponseDTO;
import com.itau.banking.transfer_api.resource.dto.TransferResponseDTO;
import com.itau.banking.transfer_api.resource.repository.ITransferClient;
import com.itau.banking.transfer_api.resource.repository.ITransferClientMock;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class TransferGatewayAdapter implements ITransferGateway {

    private final ITransferClientMock transferClientMock;
    private final ITransferClient transferClient;

    @Autowired
    public TransferGatewayAdapter(
            ITransferClient transferClient,
            ITransferClientMock transferClientMock
    ) {
        this.transferClientMock = transferClientMock;
        this.transferClient = transferClient;
    }

    @Override
    public AccountResponseDTO searchAccountById(String id) {

        try {

            log.info(LogTags.GATEWAY + " Search Account by ID: {}", id);
            var conta =  transferClient.buscarPorId(id);
            log.info(LogTags.GATEWAY + " Successfully searched Account by ID: {}", id);

            return conta;

        } catch (Exception e) {

            log.error(LogTags.GATEWAY + " Error while searching for account ID: {} , Erro: ", id, e.getMessage());
            throw e;
        }
    }

    @Override
    public ClientResponseDTO searchClientById(String id) {

        try {
            log.info(LogTags.GATEWAY + " Search Client by ID: {}", id);
            ClientResponseDTO client = transferClient.searchById(id);
            log.info(LogTags.GATEWAY + " Successfully searched Client by ID: {}", id);

            return client;

        } catch (Exception e) {

            log.error(LogTags.GATEWAY + " Error while searching for Client ID: {} , Error: ", id, e.getMessage());
            throw e;
        }
    }

    @Override
    public TransferResponseDTO transfer(TransferModel transferModel) {

        try {

            log.info(LogTags.GATEWAY + " Start transfer: {}", logTransfer(transferModel));
            var transferResponseDTO = transferClientMock.transfer(transferModel.toDTORequest(transferModel));
            log.info(LogTags.GATEWAY + " Successfully transferred! : {}", logTransfer(transferModel));

            return transferResponseDTO;

        } catch (Exception e) {
            log.error(LogTags.GATEWAY + " Error while tranfering :{} Error: {}", logTransfer(transferModel), e.getMessage());
            throw e;
        }
    }

    private String logTransfer(TransferModel transferModel) {
        return String.format("cliente: %s, conta de origem: %s, conta de destino: %s, valor: %s",
                transferModel.getIdCliente(),
                transferModel.getConta().getIdOrigem(),
                transferModel.getConta().getIdDestino(),
                transferModel.getValor());
    }

}
