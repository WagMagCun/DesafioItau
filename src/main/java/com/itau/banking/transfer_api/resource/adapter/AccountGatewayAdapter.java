package com.itau.banking.transfer_api.resource.adapter;

import com.itau.banking.transfer_api.common.LogTags;
import com.itau.banking.transfer_api.domain.contract.IAccountGateway;
import com.itau.banking.transfer_api.domain.model.AccountModel;
import com.itau.banking.transfer_api.domain.model.BalanceModel;
import com.itau.banking.transfer_api.resource.client.IAccountClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class AccountGatewayAdapter implements IAccountGateway {

    private final IAccountClient accountClient;

    @Autowired
    public AccountGatewayAdapter(
            IAccountClient accountClient
    ) {
        this.accountClient = accountClient;
    }

    @Override
    public AccountModel searchAccountById(String id) {

        try {

            log.debug(LogTags.GATEWAY + " Search Account by ID: {}", id);
            var conta =  accountClient.searchAccountById(id).toDomain();
            log.info(LogTags.GATEWAY + " Successfully searched Account by ID: {}", id);

            return conta;

        } catch (Exception e) {

            log.error(LogTags.GATEWAY + " Error while searching for account ID: {} , Erro: ", id, e.getMessage());
            throw e;
        }
    }

    @Override
    public ResponseEntity<Void> updateBalance(BalanceModel balanceModel){

        try {

            log.info(LogTags.GATEWAY + " Start update balance: {}", logTransfer(balanceModel) );
            ResponseEntity<Void> response =  accountClient.updateBalance(balanceModel.toDTORequest());
            log.info(LogTags.GATEWAY + " Successfully updated balance! : {} HTTP Status: {}", logTransfer(balanceModel),response, response.getStatusCode());
            return response;

        } catch (Exception e) {
            log.error(LogTags.GATEWAY + " Error while updating balance :{} Error: {}", logTransfer(balanceModel), e.getMessage());
            throw e;
        }

    }

    private String logTransfer(BalanceModel balanceModel) {
        return String.format("conta: %s, conta de origem: %s, conta de destino: %s, valor: %s",
                balanceModel.getBalanceAccount(),
                balanceModel.getAccount().getSourceId(),
                balanceModel.getAccount().getDestinationId(),
                balanceModel.getAmmount());
    }

}
