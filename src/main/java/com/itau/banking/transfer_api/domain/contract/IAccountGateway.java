package com.itau.banking.transfer_api.domain.contract;

import com.itau.banking.transfer_api.domain.model.AccountModel;
import com.itau.banking.transfer_api.domain.model.BalanceModel;
import org.springframework.http.ResponseEntity;

public interface IAccountGateway {

    AccountModel searchAccountById(String id);
    ResponseEntity<Void> updateBalance(BalanceModel transferModel);

}