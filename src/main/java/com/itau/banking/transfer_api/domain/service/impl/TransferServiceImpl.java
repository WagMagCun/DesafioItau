package com.itau.banking.transfer_api.domain.service.impl;

import com.itau.banking.transfer_api.domain.model.ClientModel;
import com.itau.banking.transfer_api.domain.model.AccountModel;
import com.itau.banking.transfer_api.domain.model.TransferAccountModel;
import com.itau.banking.transfer_api.domain.model.TransferModel;
import com.itau.banking.transfer_api.domain.service.contract.ITransferService;
import com.itau.banking.transfer_api.resource.adapter.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class TransferServiceImpl implements ITransferService {

    private final ITransferGateway transferenciaGateway;

    public TransferServiceImpl(
                TransferGatewayAdapter transferenciaGateway
            ) {
        this.transferenciaGateway = transferenciaGateway;
    }

    @Override
    public TransferModel transfer(TransferModel transferModel) {

        AccountModel accountModel = AccountModel.fromDTO(transferenciaGateway.searchAccountById(transferModel.getConta().getIdDestino()));

        ClientModel clientModel = ClientModel.fromDTO(transferenciaGateway.searchClientById(transferModel.getConta().getIdOrigem()));

        String idTransferencia =  transferenciaGateway.transfer(transferModel).getIdTransferencia().toString();
        transferModel.setIdTransferencia(idTransferencia);

        return transferModel;

    }

    @Override
    public void searchAccount(TransferAccountModel model) {

        transferenciaGateway.searchAccountById(model.getIdDestino());

    }

    @Override
    public void searchClient(ClientModel clientModel) {

        transferenciaGateway.searchClientById(clientModel.getId());

    }


}
