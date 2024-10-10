package com.itau.banking.transfer_api.application.web.request;

import com.itau.banking.transfer_api.domain.model.TransferModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransferRequest {

    private String idCliente;
    private double valor;
    private AccountRequest conta;

    public TransferModel toModel() {
        return new TransferModel(idCliente, valor, conta.toModel()); //@@
    }
}
