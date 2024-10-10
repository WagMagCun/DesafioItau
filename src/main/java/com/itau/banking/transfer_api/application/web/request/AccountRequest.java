package com.itau.banking.transfer_api.application.web.request;

import com.itau.banking.transfer_api.domain.model.TransferAccountModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountRequest {

    private String idOrigem;
    private String idDestino;

    public TransferAccountModel toModel() {
        return new TransferAccountModel(idOrigem, idDestino);
    }
}
