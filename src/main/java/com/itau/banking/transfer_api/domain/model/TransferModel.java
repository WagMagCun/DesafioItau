package com.itau.banking.transfer_api.domain.model;

import com.itau.banking.transfer_api.resource.dto.request.TransferDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransferModel {

    private String clientId;
    private double ammount;
    private TransferAccountModel account;
    private String idTransfer;

    public TransferModel(String clientId, double ammount, TransferAccountModel account) {
        this.clientId = clientId;
        this.ammount = ammount;
        this.account = account;
    }

}
