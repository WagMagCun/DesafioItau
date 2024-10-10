package com.itau.banking.transfer_api.domain.service.contract;

import com.itau.banking.transfer_api.domain.model.ClientModel;
import com.itau.banking.transfer_api.domain.model.TransferAccountModel;
import com.itau.banking.transfer_api.domain.model.TransferModel;

public interface ITransferService {

    TransferModel transfer(TransferModel transferModel);
    void searchClient(ClientModel clientModel);
    void searchAccount(TransferAccountModel model);

}
