package com.itau.banking.transfer_api.domain.contract;

import com.itau.banking.transfer_api.domain.model.TransferModel;
import com.itau.banking.transfer_api.domain.exception.AccountInactiveException;
import com.itau.banking.transfer_api.domain.exception.ClientNotFoundException;
import com.itau.banking.transfer_api.domain.exception.DailyLimitExceededException;
import com.itau.banking.transfer_api.domain.exception.InsufficientBalanceException;

public interface ITransferService {

    TransferModel transfer(TransferModel transferModel) throws ClientNotFoundException, AccountInactiveException, InsufficientBalanceException, DailyLimitExceededException;

}
