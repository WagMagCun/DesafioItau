package com.itau.banking.transfer_api.domain.contract;

import com.itau.banking.transfer_api.domain.model.ClientModel;

public interface IClientGateway {

    ClientModel searchClientById(String id);
}