package com.itau.banking.transfer_api.application.web.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.itau.banking.transfer_api.domain.model.TransferModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransferRequest {

    @JsonProperty("idCliente")
    private String idClient;

    @JsonProperty("valor")
    private double amount;

    @JsonProperty("conta")
    private AccountRequest account;

    public TransferModel toModel() {
        return new TransferModel(idClient, amount, account.toModel()); //@@
    }
}
