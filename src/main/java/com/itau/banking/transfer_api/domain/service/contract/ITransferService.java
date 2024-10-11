package com.itau.banking.transfer_api.domain.service.contract;

import com.itau.banking.transfer_api.domain.model.ClientModel;
import com.itau.banking.transfer_api.domain.model.TransferAccountModel;
import com.itau.banking.transfer_api.domain.model.TransferModel;
import com.itau.banking.transfer_api.exception.AccountInactiveException;
import com.itau.banking.transfer_api.exception.ClientNotFoundException;
import com.itau.banking.transfer_api.exception.DailyLimitExceededException;
import com.itau.banking.transfer_api.exception.InsufficientBalanceException;

public interface ITransferService {

    TransferModel transfer(TransferModel transferModel) throws ClientNotFoundException, AccountInactiveException, InsufficientBalanceException, DailyLimitExceededException;
    void searchClient(ClientModel clientModel);
    void searchAccount(TransferAccountModel model);

}
