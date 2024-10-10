package com.itau.banking.transfer_api.domain.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransferAccountModel {

    private String idOrigem;
    private String idDestino;

    public TransferAccountModel(String idOrigem, String idDestino) {
        this.idOrigem = idOrigem;
        this.idDestino = idDestino;
    }
}