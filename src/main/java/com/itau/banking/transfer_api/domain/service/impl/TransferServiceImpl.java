package com.itau.banking.transfer_api.domain.service.impl;

import com.itau.banking.transfer_api.domain.model.ClientModel;
import com.itau.banking.transfer_api.domain.model.AccountModel;
import com.itau.banking.transfer_api.domain.model.TransferAccountModel;
import com.itau.banking.transfer_api.domain.model.TransferModel;
import com.itau.banking.transfer_api.domain.service.contract.ITransferService;
import com.itau.banking.transfer_api.exception.*;
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
    public TransferModel transfer(TransferModel transfer)
            throws AccountInactiveException, InsufficientBalanceException, DailyLimitExceededException, ClientNotFoundException {

        validateClient(transfer.getIdCliente());

        AccountModel account = validateAccount(transfer.getConta().getIdOrigem(), transfer.getValor());

        processTransfer(transfer, account);

        notifyBacen(transfer);

        String idTransfer =  transferenciaGateway.transfer(transfer).getIdTransferencia().toString();
        transfer.setIdTransferencia(idTransfer);

        transferenciaGateway.updateBalance(transfer);

        transferenciaGateway.notifyBacen(transfer);

        return transfer;

    }

    @Override
    public void searchAccount(TransferAccountModel model) {

        transferenciaGateway.searchAccountById(model.getIdDestino());

    }

    @Override
    public void searchClient(ClientModel clientModel) {

        transferenciaGateway.searchClientById(clientModel.getId());

    }

    private void validateClient(String idCliente) throws ClientNotFoundException
    {
        ClientModel client = ClientModel.fromDTO(transferenciaGateway.searchClientById(idCliente));
        if (client.getId() == null) {
            throw new ClientNotFoundException("Cliente não encontrado para o id: " + idCliente);
        }
    }

    private AccountModel validateAccount(String idConta, double valorTransferencia)
           throws AccountInactiveException, InsufficientBalanceException, DailyLimitExceededException {

        AccountModel account = AccountModel.fromDTO(transferenciaGateway.searchAccountById(idConta));
        if (!account.getAtivo()) {
           throw new AccountInactiveException("Conta não está ativa para o id: " + idConta);
        }
        if (account.getSaldo() <= 0 || account.getSaldo() < valorTransferencia) {
           throw new InsufficientBalanceException("Saldo insuficiente para realizar a transferência.");
        }
        if (account.getLimiteDiario() <=0 || account.getLimiteDiario() < valorTransferencia) {
            throw new DailyLimitExceededException("Valor de transferência excede o limite diário.");
        }
        return account;

    }

    private void processTransfer(TransferModel transfer, AccountModel account) {
        String idTransfer = transferenciaGateway.transfer(transfer).getIdTransferencia().toString();
        transfer.setIdTransferencia(idTransfer);
        transferenciaGateway.updateBalance(transfer);
    }

    private void notifyBacen(TransferModel transfer) {
        transferenciaGateway.notifyBacen(transfer);
    }

}
